package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.services.BaiHocService;
import ut.edu.hannah.services.TienDoService;

import java.util.Optional;

/**
 * Controller để xử lý các yêu cầu liên quan đến bài học.
 */
@Controller
public class BaiHocController {
    private final BaiHocService baiHocService;
    private final TienDoService tienDoService;

    public BaiHocController(BaiHocService baiHocService, TienDoService tienDoService) {
        this.baiHocService = baiHocService;
        this.tienDoService = tienDoService;
    }

    /**
     * Hiển thị trang bài học và cập nhật tiến độ.
     * @param maKhoaHoc Mã khóa học.
     * @param maBaiHoc Mã bài học.
     * @param model Model để truyền dữ liệu.
     * @param session Session để kiểm tra người dùng.
     * @return Tên template Thymeleaf hoặc chuyển hướng.
     */
    @GetMapping("/courses/learn/{maKhoaHoc}/{maBaiHoc}")
    public String showLesson(@PathVariable Integer maKhoaHoc, @PathVariable Integer maBaiHoc, Model model, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Optional<BaiHoc> baiHoc = baiHocService.findById(maBaiHoc);
        if (baiHoc.isEmpty() || !baiHoc.get().getKhoaHoc().getMaKhoaHoc().equals(maKhoaHoc)) {
            model.addAttribute("error", "Bài học hoặc khóa học không hợp lệ");
            return "redirect:/courses";
        }

        // Cập nhật tiến độ (100% khi xem bài học)
        tienDoService.updateProgress(
            user.getMaNguoiDung(),
            maKhoaHoc,
            maBaiHoc,
            java.math.BigDecimal.valueOf(100.0),
            1,
            true
        );
        model.addAttribute("baiHoc", baiHoc.get());
        model.addAttribute("baiHoc", baiHoc.get());
        model.addAttribute("tienDo", tienDoService.findByNguoiDungAndKhoaHocAndBaiHoc(user.getMaNguoiDung(), maKhoaHoc, maBaiHoc).orElse(null));
        model.addAttribute("user", user);
        return "lesson";
    }
}