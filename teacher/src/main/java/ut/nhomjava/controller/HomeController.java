package ut.nhomjava.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
 @GetMapping("/teacher-dashboard")
    public String showHome() {
        // Phương thức này sẽ trả về tên của template HTML mà Thymeleaf sẽ render.
        // Tên template phải khớp với tên file HTML của bạn (teacher-dashboard.html)
        // mà không có phần mở rộng .html.
        return "teacher-dashboard";
    }
}
