package ut.edu.hannah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.services.TaiLieuService;
import ut.edu.hannah.services.LoaiTaiLieuService;

/**
 * Controller để xử lý các yêu cầu liên quan đến tài liệu học tập.
 */
@Controller
public class TaiLieuController {
    private final TaiLieuService taiLieuService;
    private final LoaiTaiLieuService loaiTaiLieuService;

    public TaiLieuController(TaiLieuService taiLieuService, LoaiTaiLieuService loaiTaiLieuService) {
        this.taiLieuService = taiLieuService;
        this.loaiTaiLieuService = loaiTaiLieuService;
    }

    /**
     * Hiển thị danh sách tài liệu theo bài học.
     * @param maBaiHoc Mã bài học.
     * @param model Model để truyền dữ liệu.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/documents")
    public String listTaiLieu(@RequestParam Integer maBaiHoc, Model model) {
        try {
            if (maBaiHoc == null) {
                throw new IllegalArgumentException("Mã bài học không được để trống");
            }
            model.addAttribute("taiLieuList", taiLieuService.findByBaiHoc(maBaiHoc));
            model.addAttribute("loaiTaiLieuList", loaiTaiLieuService.getAllLoaiTaiLieu());
            return "documents";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "documents";
        }
    }

    /**
     * Tạo tài liệu mới.
     * @param tenTaiLieu Tên tài liệu.
     * @param maLoaiTaiLieu Mã loại tài liệu.
     * @param maBaiHoc Mã bài học.
     * @param maTacGia Mã tác giả.
     * @param duongDan Đường dẫn file.
     * @param kichThuoc Kích thước file.
     * @param model Model để truyền lỗi (nếu có).
     * @return Chuyển hướng hoặc trang tài liệu.
     */
    @PostMapping("/documents")
    public String createTaiLieu(@RequestParam String tenTaiLieu,
                               @RequestParam Integer maLoaiTaiLieu,
                               @RequestParam Integer maBaiHoc,
                               @RequestParam Integer maTacGia,
                               @RequestParam String duongDan,
                               @RequestParam Long kichThuoc,
                               Model model) {
        try {
            if (tenTaiLieu == null || tenTaiLieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên tài liệu không được để trống");
            }
            if (maLoaiTaiLieu == null || maBaiHoc == null || maTacGia == null) {
                throw new IllegalArgumentException("Mã loại tài liệu, bài học hoặc tác giả không được để trống");
            }
            taiLieuService.createTaiLieu(tenTaiLieu, maLoaiTaiLieu, maBaiHoc, maTacGia, duongDan, kichThuoc);
            return "redirect:/documents?maBaiHoc=" + maBaiHoc;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("loaiTaiLieuList", loaiTaiLieuService.getAllLoaiTaiLieu());
            return "documents";
        }
    }
}