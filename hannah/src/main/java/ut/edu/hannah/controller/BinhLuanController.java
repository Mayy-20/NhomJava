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
                                @RequestParam Integer maKhoaHoc,
                                @RequestParam Integer maNguoiDung,
                                Model model) {
        try {
            binhLuanService.createBinhLuan(noiDung, maKhoaHoc, maNguoiDung);
            return "redirect:/courses/" + maKhoaHoc;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "course-detail";
        }
    }
}