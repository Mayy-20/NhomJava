package ut.nhomjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticsController {

    @GetMapping("/analytics")
    public String showAnalyticsPage() {
        return "teacher-analytics"; // Trùng tên file .html trong /templates
    }
}
