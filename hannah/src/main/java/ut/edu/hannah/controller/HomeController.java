package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ut.edu.hannah.model.KhoaHoc;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.services.ChuDeService;
import ut.edu.hannah.services.KhoaHocService;
import ut.edu.hannah.services.NguoiDungService;
import ut.edu.hannah.services.TienDoService; 
import ut.edu.hannah.dto.UserCourseProgressDTO; 

import java.util.List;

@Controller
@RequestMapping({"/hannah","/"})
public class HomeController {
    private final KhoaHocService khoaHocService;
    private final ChuDeService chuDeService;
    private final NguoiDungService nguoiDungService;
    private final TienDoService tienDoService; 

    public HomeController(KhoaHocService khoaHocService, ChuDeService chuDeService, NguoiDungService nguoiDungService, TienDoService tienDoService) { 
        this.khoaHocService = khoaHocService;
        this.chuDeService = chuDeService;
        this.nguoiDungService = nguoiDungService;
        this.tienDoService = tienDoService; 
    }

    @GetMapping({"/", "/home"})
    public String showHomePage(Model model) {
        List<KhoaHoc> khoaHocNoiBat = khoaHocService.findByTrangThai(KhoaHoc.TrangThai.HoatDong);

        System.out.println("Số lượng khóa học HoatDong: " + khoaHocNoiBat.size());
        for (KhoaHoc kh : khoaHocNoiBat) {
            System.out.println("Khóa học: " + kh.getTenKhoaHoc());
        }

        model.addAttribute("khoaHocList", khoaHocNoiBat);
        model.addAttribute("chuDeList", chuDeService.getAllChuDe());
        return "index";
    }

    @GetMapping("/support")
    public String support() {
        return "support";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/user-profile")
    public String userProfile(Model model, HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("user");
        if (nguoiDung == null) {
            return "redirect:/login";
        }
        model.addAttribute("nguoiDung", nguoiDung);
        return "user-profile";
    }

    @PostMapping("/user-profile")
    public String updateUserProfile(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, HttpSession session, Model model) {
        NguoiDung sessionUser = (NguoiDung) session.getAttribute("user");

        if (sessionUser == null) {
            return "redirect:/login";
        }
        nguoiDung.setMaNguoiDung(sessionUser.getMaNguoiDung());
        nguoiDung.setTenDangNhap(sessionUser.getTenDangNhap());
        nguoiDung.setMatKhau(sessionUser.getMatKhau());
        nguoiDung.setVaiTro(sessionUser.getVaiTro());
        nguoiDung.setTrangThai(sessionUser.getTrangThai());
        nguoiDung.setNgayTao(sessionUser.getNgayTao());

        nguoiDungService.save(nguoiDung);

        session.setAttribute("user", nguoiDung);
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("successMessage", "Cập nhật thành công!");
        return "user-profile";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, HttpSession session) {
        NguoiDung currentUser = (NguoiDung) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", currentUser);
        model.addAttribute("hoTenNguoiDung", currentUser.getHoTen()); 

        // Lấy và truyền dữ liệu thống kê
        long coursesInProgress = tienDoService.countCoursesInProgress(currentUser.getMaNguoiDung());
        long totalStudyHours = tienDoService.calculateTotalStudyHours(currentUser.getMaNguoiDung());
        long completedLessons = tienDoService.countCompletedLessons(currentUser.getMaNguoiDung());

        model.addAttribute("coursesInProgress", coursesInProgress);
        model.addAttribute("totalStudyHours", totalStudyHours);
        model.addAttribute("completedLessons", completedLessons);
        // Lấy và truyền danh sách khóa học của tôi với tiến độ
        List<UserCourseProgressDTO> userCourses = tienDoService.getUserCourseProgress(currentUser.getMaNguoiDung());
        model.addAttribute("userCourses", userCourses);

        return "user-dashboard";
    }
}