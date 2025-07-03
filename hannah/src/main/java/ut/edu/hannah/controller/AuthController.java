package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.services.NguoiDungService;

import java.util.Optional;

/**
 * Controller để xử lý các yêu cầu liên quan đến đăng ký và đăng nhập người dùng.
 */
@Controller
public class AuthController {
    private final NguoiDungService nguoiDungService;

    public AuthController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    /**
     * Hiển thị trang đăng ký.
     * @return Tên template Thymeleaf cho trang đăng ký.
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    /**
     * Xử lý đăng ký người dùng.
     * @param tenDangNhap Tên đăng nhập.
     * @param email Email người dùng.
     * @param matKhau Mật khẩu.
     * @param confirmPassword Xác nhận mật khẩu.
     * @param hoTen Họ tên người dùng.
     * @param maVaiTro Mã vai trò.
     * @param model Model để truyền lỗi (nếu có).
     * @return Chuyển hướng hoặc trang đăng ký.
     */
    @PostMapping("/register")
    public String register(@RequestParam String tenDangNhap,
                          @RequestParam String email,
                          @RequestParam String matKhau,
                          @RequestParam String confirmPassword,
                          @RequestParam String hoTen,
                          @RequestParam Integer maVaiTro,
                          Model model) {
        try {
            nguoiDungService.register(tenDangNhap, email, matKhau, confirmPassword, hoTen, maVaiTro);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    /**
     * Hiển thị trang đăng nhập.
     * @return Tên template Thymeleaf cho trang đăng nhập.
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    /**
     * Xử lý đăng nhập người dùng.
     * @param tenDangNhap Tên đăng nhập.
     * @param matKhau Mật khẩu.
     * @param model Model để truyền lỗi hoặc dữ liệu người dùng.
     * @param session Session để lưu thông tin người dùng.
     * @return Chuyển hướng hoặc trang đăng nhập.
     */
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
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }

    /**
     * Xử lý đăng xuất.
     * @param session Session để xóa thông tin người dùng.
     * @return Chuyển hướng đến trang đăng nhập.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}