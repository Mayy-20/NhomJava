package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession; // Import HttpSession
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ut.edu.hannah.model.BaiDang;
import ut.edu.hannah.model.NguoiDung; 
import ut.edu.hannah.services.BaiDangService;
import ut.edu.hannah.services.ChuDeService;
import ut.edu.hannah.services.BinhLuanService; 

/**
 * Controller để xử lý các yêu cầu liên quan đến bài đăng trong cộng đồng.
 */
@Controller
public class BaiDangController {
    private final BaiDangService baiDangService;
    private final ChuDeService chuDeService;
    private final BinhLuanService binhLuanService; 

    public BaiDangController(BaiDangService baiDangService, ChuDeService chuDeService, BinhLuanService binhLuanService) {
        this.baiDangService = baiDangService;
        this.chuDeService = chuDeService;
        this.binhLuanService = binhLuanService; 
    }

    /**
     * Hiển thị danh sách bài đăng theo chủ đề hoặc trạng thái.
     * @param maChuDe Mã chủ đề (tùy chọn).
     * @param model Model để truyền dữ liệu tới view.
     * @param session HttpSession để kiểm tra trạng thái đăng nhập.
     * @return Tên template Thymeleaf.
     */
    @GetMapping("/community")
    public String listBaiDang(@RequestParam(required = false) Integer maChuDe,
                              Model model,
                              HttpSession session) { 
        List<BaiDang> baiDangList;
        if (maChuDe != null && maChuDe != 0) {
            baiDangList = baiDangService.findByChuDe(maChuDe);
        } else {
             baiDangList = baiDangService.getBaiDangDaDuyet(); 
        }
        baiDangList.forEach(baiDang -> {
            baiDang.setBinhLuanList(binhLuanService.findByBaiDang(baiDang.getMaBaiDang())); 
        });
        String successMessage = (String) session.getAttribute("successMessage");
            if (successMessage != null) {
             model.addAttribute("successMessage", successMessage);
             session.removeAttribute("successMessage"); 
}

        model.addAttribute("baiDangList", baiDangList);
        model.addAttribute("chuDeList", chuDeService.getAllChuDe());
        NguoiDung currentUser = (NguoiDung) session.getAttribute("user");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isLoggedIn", currentUser != null); 
      

        return "community";
    }

    /**
     * Xử lý tạo bài đăng mới.
     * @param tieuDe Tiêu đề bài đăng.
     * @param noiDung Nội dung bài đăng.
     * @param maChuDe Mã chủ đề (tùy chọn).
     * @param maBaiHoc Mã bài học (tùy chọn).
     * @param session HttpSession để lấy thông tin người dùng đã đăng nhập.
     * @param model Model để truyền lỗi (nếu có).
     * @return Chuyển hướng hoặc trang community.
     */
    @PostMapping("/community")
    public String createBaiDang(@RequestParam String tieuDe,
                               @RequestParam String noiDung,
                               @RequestParam(required = false) Integer maChuDe,
                               @RequestParam(required = false) Integer maBaiHoc,
                               HttpSession session, 
                               Model model) {
        NguoiDung currentUser = (NguoiDung) session.getAttribute("user");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (currentUser == null) {
            return "redirect:/login"; 
        }

        try {
            if (tieuDe == null || tieuDe.trim().isEmpty()) {
                throw new IllegalArgumentException("Tiêu đề không được để trống");
            }
            if (noiDung == null || noiDung.trim().isEmpty()) {
                throw new IllegalArgumentException("Nội dung không được để trống");
            }
            // Gọi service để tạo bài đăng, truyền maTacGia từ currentUser
            baiDangService.createBaiDang(tieuDe, noiDung, currentUser.getMaNguoiDung(), maChuDe, maBaiHoc);
            session.setAttribute("successMessage", "Bài đăng đã được gửi và đang chờ phê duyệt.");
            return "redirect:/community";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("chuDeList", chuDeService.getAllChuDe());
            model.addAttribute("currentUser", currentUser); 
            model.addAttribute("isLoggedIn", true); 
            // Giữ lại các giá trị đã nhập để người dùng không phải nhập lại
            model.addAttribute("tieuDe", tieuDe);
            model.addAttribute("noiDung", noiDung);
            model.addAttribute("maChuDe", maChuDe);
            model.addAttribute("maBaiHoc", maBaiHoc);
            return "community";
        }
    }
}