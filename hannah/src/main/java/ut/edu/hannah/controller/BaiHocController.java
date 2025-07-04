package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.hannah.model.*;
import ut.edu.hannah.services.*;

import java.util.Optional;

@Controller
public class BaiHocController {
    private final BaiHocService baiHocService;
    private final TienDoService tienDoService;
    private final KhoaHocService khoaHocService;

    public BaiHocController(BaiHocService baiHocService, TienDoService tienDoService, KhoaHocService khoaHocService) {
        this.baiHocService = baiHocService;
        this.tienDoService = tienDoService;
        this.khoaHocService = khoaHocService;
    }

    @GetMapping("/courses/lesson/{maKhoaHoc}/{maBaiHoc}")
    public String showLesson(@PathVariable Integer maKhoaHoc,
                            @PathVariable Integer maBaiHoc,
                            Model model,
                            HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Vui lòng đăng nhập để truy cập bài học.");
            return "redirect:/login";
        }

        Optional<BaiHoc> baiHocOpt = baiHocService.findById(maBaiHoc);
        if (baiHocOpt.isEmpty() || !baiHocOpt.get().getKhoaHoc().getMaKhoaHoc().equals(maKhoaHoc)) {
            System.out.println("Lỗi: Bài học không tồn tại hoặc không thuộc khóa học. MaKhoaHoc=" + maKhoaHoc + ", MaBaiHoc=" + maBaiHoc);
            model.addAttribute("error", "Bài học hoặc khóa học không hợp lệ");
            return "redirect:/courses";
        }

        BaiHoc baiHoc = baiHocOpt.get();
        tienDoService.updateProgress(user.getMaNguoiDung(), maKhoaHoc, maBaiHoc, 100.0f, 0, true);
        Optional<TienDo> tienDoOpt = tienDoService.findByNguoiDungAndKhoaHocAndBaiHoc(
                user.getMaNguoiDung(), maKhoaHoc, maBaiHoc);

        String embedUrl = convertToEmbedUrl(baiHoc.getVideoURL());
        System.out.println("Video URL gốc: " + baiHoc.getVideoURL());
        System.out.println("Video URL nhúng: " + embedUrl);

        model.addAttribute("baiHoc", baiHoc);
        model.addAttribute("tienDo", tienDoOpt.orElse(null));
        model.addAttribute("videoEmbedUrl", embedUrl);
        model.addAttribute("user", user);

        // Thêm thông tin khóa học và danh sách bài học để hiển thị trong giao diện
        Optional<KhoaHoc> khoaHocOpt = khoaHocService.findById(maKhoaHoc);
        if (khoaHocOpt.isPresent()) {
            model.addAttribute("course", khoaHocOpt.get());
            model.addAttribute("lessons", baiHocService.findByKhoaHoc(maKhoaHoc));
        }

        return "learning";
    }

    private String convertToEmbedUrl(String url) {
        if (url == null || url.isEmpty()) {
            System.out.println("URL video rỗng hoặc null");
            return "";
        }
        try {
            String videoId = "";
            if (url.contains("watch?v=")) {
                videoId = url.substring(url.indexOf("v=") + 2);
                int endIndex = videoId.indexOf('&') > 0 ? videoId.indexOf('&') : videoId.length();
                videoId = videoId.substring(0, endIndex);
            } else if (url.contains("youtu.be/")) {
                videoId = url.substring(url.lastIndexOf("/") + 1);
                int endIndex = videoId.indexOf('?') > 0 ? videoId.indexOf('?') : videoId.length();
                videoId = videoId.substring(0, endIndex);
            }
            if (!videoId.isEmpty()) {
                return "https://www.youtube.com/embed/" + videoId;
            }
            System.out.println("URL không được hỗ trợ: " + url);
            return "";
        } catch (Exception e) {
            System.out.println("Lỗi chuyển đổi URL: " + e.getMessage());
            return "";
        }
    }
}