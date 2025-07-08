package ut.nhomjava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/list")
    public String listStudents(Model model) {
        List<NguoiDung> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "teacher-students"; // Trả về tên của file HTML template
    }

    // Các mapping khác cho xem chi tiết học viên, xóa học viên, v.v. có thể được thêm vào đây
    // Ví dụ cho xem chi tiết học viên (nếu bạn có trang student-details.html)
    // @GetMapping("/students/{id}")
    // public String viewStudentDetails(@PathVariable Integer id, Model model) {
    //     Optional<NguoiDung> studentOptional = studentService.getStudentById(id);
    //     if (studentOptional.isPresent()) {
    //         model.addAttribute("student", studentOptional.get());
    //         return "student-details";
    //     }
    //     return "redirect:/students/list"; // Hoặc trang lỗi
    // }
}