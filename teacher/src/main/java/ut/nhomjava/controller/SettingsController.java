package ut.nhomjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    @GetMapping("/teacher-settings")
    public String showSettingsPage() {
        return "teacher-settings"; // Tên file HTML đặt trong thư mục: templates/teacher-settings.html
    }
}
