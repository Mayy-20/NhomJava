package ut.edu.hannah.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.services.TienDoService;

/**
 * Controller để xử lý các yêu cầu liên quan đến tiến độ học tập.
 */
@Controller
public class TienDoController {
    private final TienDoService tienDoService;

    public TienDoController(TienDoService tienDoService) {
        this.tienDoService = tienDoService;
    }

    /**
     * Hiển thị danh sách tiến độ của người dùng.
     * @param maNguoiDung Mã người dùng.
     * @param model Model để truyền dữ liệu.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/progress")
    public String listTienDo(@RequestParam Integer maNguoiDung, Model model) {
        try {
            if (maNguoiDung == null) {
                throw new IllegalArgumentException("Mã người dùng không được để trống");
            }
            model.addAttribute("tienDoList", tienDoService.findByNguoiDung(maNguoiDung));
            return "progress";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "progress";
        }
    }

    /**
     * Cập nhật tiến độ học tập.
     * @param maNguoiDung Mã người dùng.
     * @param maKhoaHoc Mã khóa học.
     * @param maBaiHoc Mã bài học.
     * @param phanTram Phần trăm hoàn thành.
     * @param thoiGianHoc Thời gian học (giây).
     * @param model Model để truyền lỗi (nếu có).
     * @return Chuyển hướng hoặc trang tiến độ.
     */
    @PostMapping("/progress")
    public String updateTienDo(@RequestParam Integer maNguoiDung,
                              @RequestParam Integer maKhoaHoc,
                              @RequestParam Integer maBaiHoc,
                              @RequestParam BigDecimal phanTram,
                              @RequestParam Integer thoiGianHoc,
                              Model model) {
        try {
            if (maNguoiDung == null || maKhoaHoc == null || maBaiHoc == null) {
                throw new IllegalArgumentException("Mã người dùng, khóa học hoặc bài học không được để trống");
            }
            if (phanTram == null || phanTram.compareTo(BigDecimal.ZERO) < 0 || phanTram.compareTo(BigDecimal.valueOf(100)) > 0) {
                throw new IllegalArgumentException("Phần trăm hoàn thành phải từ 0 đến 100");
            }
            tienDoService.updateProgress(maNguoiDung, maKhoaHoc, maBaiHoc, phanTram, thoiGianHoc, false);
            return "redirect:/progress?maNguoiDung=" + maNguoiDung;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "progress";
        }
    }
}