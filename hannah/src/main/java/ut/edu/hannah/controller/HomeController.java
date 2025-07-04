package ut.edu.hannah.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ut.edu.hannah.model.KhoaHoc;
import ut.edu.hannah.services.ChuDeService;
import ut.edu.hannah.services.KhoaHocService;

import java.util.List;

@Controller
@RequestMapping("/hannah")
public class HomeController {
    private final KhoaHocService khoaHocService;
    private final ChuDeService chuDeService;

    public HomeController(KhoaHocService khoaHocService, ChuDeService chuDeService) {
        this.khoaHocService = khoaHocService;
        this.chuDeService = chuDeService;
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
        model.addAttribute("user", session.getAttribute("user"));
        return "user-profile";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        return "user-dashboard";
    }
}