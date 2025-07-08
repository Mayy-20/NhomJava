package ut.nhomjava.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.service.NguoiDungService;

@Controller
@RequestMapping("/")
public class AuthController {
    private final NguoiDungService nguoiDungService;

    public AuthController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }
@GetMapping("/register")
public String showRegisterPage(Model model) {
    model.addAttribute("nguoiDung", new NguoiDung()); 
    return "register";
}

   @PostMapping("/register")
public String register(@RequestParam String tenDangNhap,
                       @RequestParam String email,
                       @RequestParam String matKhau,
                       @RequestParam String confirmPassword,
                       @RequestParam String hoTen,
                       @RequestParam(defaultValue = "3") Integer maVaiTro,
                       Model model) {
    try {
        nguoiDungService.register(tenDangNhap, email, matKhau, confirmPassword, hoTen, maVaiTro, hoTen);
        return "redirect:/login"; 
    } catch (IllegalArgumentException e) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenDangNhap(tenDangNhap);
        nguoiDung.setEmail(email);
        nguoiDung.setHoTen(hoTen);
        nguoiDung.setMatKhau(matKhau); 
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("error", e.getMessage());
        return "register"; 
    }
}



    @GetMapping("/login")
public String showLoginPage(Model model) {
    model.addAttribute("nguoiDung", new NguoiDung()); 
    return "login";
}
    @PostMapping("/login")
    public String login(@RequestParam String tenDangNhap,
                        @RequestParam String matKhau,
                        Model model,
                        HttpSession session) {
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty() || matKhau == null || matKhau.trim().isEmpty()) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không được để trống");
            return "login";
        }
        Optional<NguoiDung> nguoiDung = nguoiDungService.login(tenDangNhap, matKhau);
        if (nguoiDung.isPresent()) {
            session.setAttribute("user", nguoiDung.get());
            return "redirect:/hannah/user-dashboard";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}