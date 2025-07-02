package ut.edu.hannah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.model.*;
import ut.edu.hannah.services.BaiHocService;
import ut.edu.hannah.services.BinhLuanService;
import ut.edu.hannah.services.ChuDeService;
import ut.edu.hannah.services.KhoaHocService;
import ut.edu.hannah.services.TienDoService;

import java.util.List;
import java.util.Optional;

/**
 * Controller để xử lý các yêu cầu liên quan đến khóa học.
 */
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

    /**
     * Hiển thị danh sách khóa học, lọc theo chủ đề nếu có.
     * @param topic Tên chủ đề (tùy chọn).
     * @param model Model để truyền dữ liệu tới view.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/courses")
    public String listCourses(@RequestParam(required = false) String topic, Model model) {
        List<KhoaHoc> khoaHocList = (topic != null && !topic.trim().isEmpty()) ?
            khoaHocService.findByChuDe(topic) :
            khoaHocService.getAllKhoaHoc();
        model.addAttribute("khoaHocList", khoaHocList);
        model.addAttribute("chuDeList", chuDeService.getAllChuDe());
        model.addAttribute("selectedTopic", topic); // Truyền topic để đánh dấu tab active
        return "courses";
    }

    /**
     * Hiển thị chi tiết khóa học.
     * @param id Mã khóa học.
     * @param model Model để truyền dữ liệu hoặc lỗi.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/courses/{id}")
    public String viewCourse(@PathVariable Integer id, Model model) {
        if (id == null) {
            model.addAttribute("error", "Mã khóa học không hợp lệ");
            return "course-detail";
        }
        Optional<KhoaHoc> khoaHocOpt = khoaHocService.findById(id);
        if (khoaHocOpt.isPresent()) {
            KhoaHoc khoaHoc = khoaHocOpt.get();
            List<BaiHoc> baiHocList = baiHocService.findByKhoaHoc(id);
            List<BinhLuan> binhLuanList = binhLuanService.findByKhoaHoc(id);
            TienDo tienDo = tienDoService.findByKhoaHocAndNguoiDung(id, getCurrentUserId());
            model.addAttribute("khoaHoc", khoaHoc);
            model.addAttribute("baiHocList", baiHocList);
            model.addAttribute("binhLuanList", binhLuanList);
            model.addAttribute("tienDo", tienDo);
            return "course-detail";
        }
        model.addAttribute("error", "Khóa học không tồn tại");
        return "course-detail";
    }

    /**
     * Hiển thị trang xem trước khóa học.
     * @param id Mã khóa học.
     * @param model Model để truyền dữ liệu.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/courses/preview/{id}")
    public String previewCourse(@PathVariable Integer id, Model model) {
        Optional<KhoaHoc> khoaHocOpt = khoaHocService.findById(id);
        if (khoaHocOpt.isPresent()) {
            KhoaHoc khoaHoc = khoaHocOpt.get();
            List<BaiHoc> baiHocList = baiHocService.findByKhoaHoc(id);
            model.addAttribute("khoaHoc", khoaHoc);
            model.addAttribute("baiHocList", baiHocList);
            return "course-preview";
        }
        model.addAttribute("error", "Khóa học không tồn tại");
        return "redirect:/courses";
    }

    // Phương thức giả định để lấy ID người dùng hiện tại (cần triển khai thực tế)
    private Integer getCurrentUserId() {
        // Logic lấy ID từ session hoặc security context
        return 1; // Giá trị mẫu
    }
}