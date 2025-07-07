package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.model.NguoiDung; 
import ut.edu.hannah.services.BinhLuanService;
import ut.edu.hannah.services.BaiDangService; 

/**
 * Controller để xử lý các yêu cầu liên quan đến bình luận của bài đăng.
 */
@Controller
public class BinhLuanController {
    private final BinhLuanService binhLuanService;
    private final BaiDangService baiDangService; 

    public BinhLuanController(BinhLuanService binhLuanService, BaiDangService baiDangService) {
        this.binhLuanService = binhLuanService;
        this.baiDangService = baiDangService;
    }

    @PostMapping("/comments")
    public String createBinhLuan(@RequestParam String noiDung,
                                 @RequestParam Integer maBaiDang,
                                 HttpSession session, 
                                 Model model) {
        NguoiDung currentUser = (NguoiDung) session.getAttribute("user");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (currentUser == null) {
            return "redirect:/login"; 
        }

        try {
            if (noiDung == null || noiDung.trim().isEmpty()) {
                throw new IllegalArgumentException("Nội dung bình luận không được để trống");
            }
            binhLuanService.createBinhLuan(noiDung, maBaiDang, currentUser.getMaNguoiDung());
            return "redirect:/community"; 
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/community?error=" + e.getMessage(); 
        }
    }
}