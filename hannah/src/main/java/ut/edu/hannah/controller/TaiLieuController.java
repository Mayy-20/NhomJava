package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.model.TaiLieu;
import ut.edu.hannah.model.NguoiDung; 
import ut.edu.hannah.services.TaiLieuService;
import ut.edu.hannah.services.LoaiTaiLieuService;

import java.util.List;
import java.util.Optional; 

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
     * Hiển thị danh sách tài liệu với tùy chọn lọc theo loại tài liệu.
     * @param loaiTaiLieuId ID loại tài liệu để lọc (tùy chọn). Nếu null hoặc 0, hiển thị tất cả.
     * @param model Model để truyền dữ liệu.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/documents")
    public String showDocuments(@RequestParam(name = "loaiTaiLieuId", required = false) Integer loaiTaiLieuId,
                                Model model,
                                HttpSession session) { 
        List<TaiLieu> taiLieuList;
        if (loaiTaiLieuId != null && loaiTaiLieuId != 0) {
            taiLieuList = taiLieuService.findByLoaiTaiLieu(loaiTaiLieuId);
        } else {
            taiLieuList = taiLieuService.getAllTaiLieu();
        }
        model.addAttribute("taiLieuList", taiLieuList);
        model.addAttribute("loaiTaiLieuOptions", loaiTaiLieuService.getAllLoaiTaiLieu());
        model.addAttribute("selectedLoaiTaiLieuId", loaiTaiLieuId);
        model.addAttribute("isLoggedIn", session.getAttribute("user") != null);

        return "documents";
    }

    @PostMapping("/documents/create")
    public String createDocument(@RequestParam String tenTaiLieu,
                               @RequestParam(required = false) String moTa,
                               @RequestParam Integer maLoaiTaiLieu,
                               @RequestParam Integer maBaiHoc,
                               @RequestParam Integer maTacGia,
                               @RequestParam String duongDan,
                               @RequestParam Long kichThuoc,
                               Model model) {
        try {
            System.out.println("KichThuoc received: " + kichThuoc);

            if (tenTaiLieu == null || tenTaiLieu.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên tài liệu không được để trống");
            }
            if (maLoaiTaiLieu == null || maBaiHoc == null || maTacGia == null) {
                throw new IllegalArgumentException("Mã loại tài liệu, bài học hoặc tác giả không được để trống");
            }
            // Gọi service để tạo tài liệu
            taiLieuService.createTaiLieu(tenTaiLieu, moTa, maLoaiTaiLieu, maBaiHoc, maTacGia, duongDan, kichThuoc);
            return "redirect:/documents"; 
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("loaiTaiLieuOptions", loaiTaiLieuService.getAllLoaiTaiLieu());
            return "documents"; 
        }
    }

    /**
     * Endpoint để xử lý yêu cầu mở tài liệu.
     * Kiểm tra trạng thái đăng nhập trước khi chuyển hướng đến đường dẫn tài liệu thực tế.
     * @param maTaiLieu Mã tài liệu cần mở.
     * @param session Đối tượng HttpSession để kiểm tra trạng thái đăng nhập.
     * @return Chuyển hướng đến đường dẫn tài liệu hoặc trang đăng nhập.
     */
    @GetMapping("/documents/open")
    public String openDocument(@RequestParam Integer maTaiLieu, HttpSession session) {
        NguoiDung currentUser = (NguoiDung) session.getAttribute("user");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (currentUser == null) {
            return "redirect:/login"; 
        }

        // Nếu đã đăng nhập, tìm tài liệu và chuyển hướng đến đường dẫn của nó
        Optional<TaiLieu> taiLieuOptional = taiLieuService.findById(maTaiLieu);
        if (taiLieuOptional.isPresent()) {
            TaiLieu taiLieu = taiLieuOptional.get();
            return "redirect:" + taiLieu.getDuongDan();
        } else {
            return "redirect:/documents?error=documentNotFound"; 
        }
    }
}