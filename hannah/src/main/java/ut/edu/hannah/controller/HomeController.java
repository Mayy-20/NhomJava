package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.service.NguoiDungService;

@Controller
public class HomeController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping({"/", "/index"})
    public String index(HttpSession session) {
        if (session.getAttribute("user") != null) {
            NguoiDung user = (NguoiDung) session.getAttribute("user");
            return redirectToDashboard(user.getMaVaiTro());
        }
        return "index";
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }

    @GetMapping("/course-detail")
    public String courseDetail() {
        return "course-detail";
    }

    @GetMapping("/learning")
    public String learning() {
        return "learning";
    }

    @GetMapping("/documents")
    public String documents() {
        return "documents";
    }

    @GetMapping("/community")
    public String community() {
        return "community";
    }

    @GetMapping("/support")
    public String support() {
        return "support";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, HttpSession session, Model model) {
        NguoiDung authenticatedUser = nguoiDungService.login(nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau());
        if (authenticatedUser != null) {
            session.setAttribute("user", authenticatedUser);
            return redirectToDashboard(authenticatedUser.getMaVaiTro());
        }
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        model.addAttribute("nguoiDung", nguoiDung);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        NguoiDung registeredUser = nguoiDungService.register(nguoiDung, confirmPassword);
        if (registeredUser != null) {
            return "redirect:/login"; // Chuyển hướng về trang đăng nhập
        }
        String error = nguoiDung.getMatKhau().equals(confirmPassword) ? "Tên đăng nhập hoặc email đã tồn tại" : "Mật khẩu xác nhận không khớp";
        model.addAttribute("error", error);
        model.addAttribute("nguoiDung", nguoiDung);
        return "register";
    }

    @GetMapping("/user-profile")
    public String userProfile() {
        return "user-profile";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    private String redirectToDashboard(Integer maVaiTro) {
        switch (maVaiTro) {
            case 1:
                return "redirect:/admin-dashboard";
            case 2:
                return "redirect:/teacher-dashboard";
            case 3:
            default:
                return "redirect:/user-dashboard";
        }
    }
}