package ut.edu.hannah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
   @GetMapping({"/", "/index"})
public String index() {
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
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
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
}
