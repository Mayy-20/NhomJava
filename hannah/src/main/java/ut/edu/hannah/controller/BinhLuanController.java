package ut.edu.hannah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.services.BinhLuanService;

/**
 * Controller để xử lý các yêu cầu liên quan đến bình luận của khóa học.
 */
@Controller
public class BinhLuanController {
    private final BinhLuanService binhLuanService;

    public BinhLuanController(BinhLuanService binhLuanService) {
        this.binhLuanService = binhLuanService;
    }
@PostMapping("/comments")
public String createBinhLuan(@RequestParam String noiDung,
                             @RequestParam Integer maBaiDang,
                             @RequestParam Integer maNguoiDung,
                             Model model) {
    try {
        binhLuanService.createBinhLuan(noiDung, maBaiDang, maNguoiDung);
        return "redirect:/community"; // Chuyển hướng đến trang cộng đồng
    } catch (IllegalArgumentException e) {
        model.addAttribute("error", e.getMessage());
        return "community"; // Trả về trang cộng đồng nếu có lỗi
    }
}
}