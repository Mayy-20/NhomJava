package ut.nhomjava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ut.nhomjava.model.Achievement;
import ut.nhomjava.model.Teacher;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String showProfile(Model model) {
        Teacher teacher = new Teacher();
        teacher.setFullName("Nguyễn Văn A");
        teacher.setEmail("van.a@example.com");
        teacher.setPhone("0123 456 789");
        teacher.setJoinDate("10/01/2024");
        teacher.setPosition("Giáo viên Python");
        teacher.setBio("Tôi là một giáo viên đam mê giảng dạy Python và Data Science.");
        teacher.setSkills(List.of("Python", "Data Science", "Web Development"));
        teacher.setAchievements(List.of(
            new Achievement("Giáo viên xuất sắc", "2024"),
            new Achievement("Chứng chỉ Python", "2023")
        ));

        model.addAttribute("teacher", teacher);
        return "teacher-profile";
    }
    
}
