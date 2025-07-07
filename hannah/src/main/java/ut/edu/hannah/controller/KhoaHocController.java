package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ut.edu.hannah.model.*;
import ut.edu.hannah.services.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class KhoaHocController {

    private final KhoaHocService khoaHocService;
    private final ChuDeService chuDeService;
    private final BaiHocService baiHocService;
    private final BinhLuanService binhLuanService;
    private final TienDoService tienDoService;

    public KhoaHocController(KhoaHocService khoaHocService, ChuDeService chuDeService,
                             BaiHocService baiHocService, BinhLuanService binhLuanService,
                             TienDoService tienDoService) {
        this.khoaHocService = khoaHocService;
        this.chuDeService = chuDeService;
        this.baiHocService = baiHocService;
        this.binhLuanService = binhLuanService;
        this.tienDoService = tienDoService;
    }

    @GetMapping("/courses")
    public String listCourses(@RequestParam(required = false) Integer topic, Model model) {
        List<KhoaHoc> khoaHocList = (topic != null)
                ? khoaHocService.findByChuDe(topic)
                : khoaHocService.getAllKhoaHoc();
        model.addAttribute("khoaHocList", khoaHocList);
        model.addAttribute("chuDeList", chuDeService.getAllChuDe());
        model.addAttribute("selectedTopic", topic);
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String redirectToPreview(@PathVariable Integer id) {
        return "redirect:/courses/preview/" + id;
    }

    @GetMapping("/courses/preview/{id}")
    public String previewCourse(@PathVariable Integer id, Model model) {
        Optional<KhoaHoc> khoaHocOpt = khoaHocService.findById(id);
        String totalDuration = baiHocService.calculateTotalDuration(id);
        model.addAttribute("totalDuration", totalDuration);
        if (khoaHocOpt.isEmpty()) {
            model.addAttribute("error", "Khóa học không tồn tại");
            return "redirect:/courses";
        }

        KhoaHoc khoaHoc = khoaHocOpt.get();
        List<BaiHoc> baiHocList = baiHocService.findByKhoaHoc(id);
        model.addAttribute("khoaHoc", khoaHoc);
        model.addAttribute("baiHocList", baiHocList);
        return "course-detail";
    }

    @GetMapping("/courses/start/{id}")
    public String startLearning(@PathVariable Integer id,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "Bạn cần đăng nhập để học khóa học này.");
            return "redirect:/login";
        }

        List<BaiHoc> baiHocList = baiHocService.findByKhoaHoc(id);
        if (baiHocList == null || baiHocList.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Khóa học này chưa có bài học.");
            return "redirect:/courses";
        }

        baiHocList.sort(Comparator.comparingInt(BaiHoc::getThuTu));

        Map<Integer, TienDo> tienDoMap = tienDoService.getAllByKhoaHocAndNguoiDungAsMap(id, user.getMaNguoiDung());

        for (int i = 0; i < baiHocList.size(); i++) {
            BaiHoc bh = baiHocList.get(i);
            TienDo td = tienDoMap.get(bh.getMaBaiHoc());
            if (td == null || !Boolean.TRUE.equals(td.getHoanThanh())) {
                return "redirect:/courses/learning/" + id + "?lesson=" + (i + 1);
            }
        }

        // Nếu học xong hết -> mở lại bài cuối
        return "redirect:/courses/learning/" + id + "?lesson=" + baiHocList.size();
    }

    @GetMapping("/courses/learning/{id}")
    public String viewLearningPage(@PathVariable Integer id,
                                   @RequestParam(required = false) Integer lesson,
                                   HttpSession session,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "Bạn cần đăng nhập để học khóa học này.");
            return "redirect:/login";
        }

        Optional<KhoaHoc> khoaHocOpt = khoaHocService.findById(id);
        if (khoaHocOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Khóa học không tồn tại.");
            return "redirect:/courses";
        }

        KhoaHoc khoaHoc = khoaHocOpt.get();
        List<BaiHoc> baiHocList = baiHocService.findByKhoaHoc(id);
        if (baiHocList.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Khóa học này chưa có bài học.");
            return "redirect:/courses";
        }

        int lessonIndex = (lesson != null && lesson > 0 && lesson <= baiHocList.size()) ? lesson - 1 : 0;
        BaiHoc currentLesson = baiHocList.get(lessonIndex);

        tienDoService.updateProgress(user.getMaNguoiDung(), id, currentLesson.getMaBaiHoc(),
                new java.math.BigDecimal("100.0"), 0, true);
        Map<Integer, TienDo> tienDoMap = tienDoService.getAllByKhoaHocAndNguoiDungAsMap(id, user.getMaNguoiDung());
        List<BinhLuan> binhLuanList = binhLuanService.findByKhoaHoc(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        model.addAttribute("course", khoaHoc);
        model.addAttribute("lessons", baiHocList);
        model.addAttribute("tienDoMap", tienDoMap);
        model.addAttribute("user", user);
        model.addAttribute("currentLesson", currentLesson);
        model.addAttribute("baiHoc", currentLesson);
        model.addAttribute("videoEmbedUrl", convertToEmbedUrl(currentLesson.getVideoURL()));
        model.addAttribute("lessonIndex", lessonIndex + 1);
        model.addAttribute("previousLesson", lessonIndex > 0 ? baiHocList.get(lessonIndex - 1) : null);
        model.addAttribute("nextLesson", lessonIndex < baiHocList.size() - 1 ? baiHocList.get(lessonIndex + 1) : null);
        model.addAttribute("binhLuanList", binhLuanList);
        model.addAttribute("ngayTaoFormatted", khoaHoc.getNgayTao().format(formatter));

        return "learning";
    }

    private String convertToEmbedUrl(String url) {
        if (url == null || url.isEmpty()) return null;
        try {
            if (url.contains("watch?v=")) return url.replace("watch?v=", "embed/");
            if (url.contains("youtu.be/")) return url.replace("youtu.be/", "www.youtube.com/embed/");
        } catch (Exception e) {
            System.out.println("Lỗi chuyển đổi video URL: " + e.getMessage());
        }
        return url;
    }
}
