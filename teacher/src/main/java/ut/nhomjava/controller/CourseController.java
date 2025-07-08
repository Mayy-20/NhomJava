package ut.nhomjava.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ut.nhomjava.model.BaiHoc;
import ut.nhomjava.model.KhoaHoc; // Để lấy thông tin giảng viên
import ut.nhomjava.model.KhoaHoc.CapDo;
import ut.nhomjava.model.KhoaHoc.TrangThai;
import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.service.BaiHocService;
import ut.nhomjava.service.KhoaHocService;
import ut.nhomjava.service.NguoiDungService;

@Controller
@RequestMapping("/teacher-courses")
public class CourseController {

    @Autowired
    private KhoaHocService khoaHocService;

    @Autowired
    private BaiHocService baiHocService;

    @Autowired
    private NguoiDungService nguoiDungService; // Để gán giảng viên cho khóa học

    // Hiển thị danh sách khóa học
    @GetMapping
    public String listCourses(Model model) {
        List<KhoaHoc> courses = khoaHocService.findAll();
        model.addAttribute("courses", courses);
        return "teacher-courses"; // Tên template HTML: teacher-courses.html
    }

    // Hiển thị form tạo khóa học mới
    @GetMapping("/create")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("khoaHoc", new KhoaHoc());
        model.addAttribute("capDoEnums", CapDo.values()); // Để hiển thị các lựa chọn cấp độ
        return "teacher-create-course"; // Tên template HTML: teacher-create-course.html
    }

    // Xử lý lưu khóa học mới (hoặc cập nhật)
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("khoaHoc") KhoaHoc khoaHoc) {
        // Gán giảng viên hiện tại cho khóa học (giả định có phương thức getCurrentUser() trong NguoiDungService)
        NguoiDung giangVien = nguoiDungService.getCurrentUser(); // Lấy người dùng hiện tại
        if (giangVien != null) {
            khoaHoc.setGiangVien(giangVien);
        } else {
            // Xử lý nếu không tìm thấy người dùng hiện tại (ví dụ: chuyển hướng đến trang đăng nhập)
            return "redirect:/login";
        }

        // Đặt trạng thái và ngày tạo mặc định nếu là khóa học mới
        if (khoaHoc.getMaKhoaHoc() == null) {
            khoaHoc.setTrangThai(TrangThai.ChoDuyet); // Mặc định là Chờ Duyệt
            khoaHoc.setNgayTao(LocalDateTime.now());
        }
        khoaHocService.save(khoaHoc);
        return "redirect:/teacher-courses";
    }

    // Hiển thị chi tiết khóa học và các bài học
    @GetMapping("/{id}")
    public String viewCourseDetails(@PathVariable Integer id, Model model) {
        KhoaHoc khoaHoc = khoaHocService.findById(id);
        if (khoaHoc == null) {
            return "redirect:/teacher-courses";
        }
        model.addAttribute("course", khoaHoc);
        model.addAttribute("newLesson", new BaiHoc()); // Đối tượng cho form tạo bài học mới
        return "teacher-course-details"; // Tên template HTML: teacher-course-details.html
    }

    // Xử lý xóa khóa học
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        khoaHocService.deleteById(id);
        return "redirect:/teacher-courses";
    }

    // Thêm bài học mới vào khóa học
    @PostMapping("/{courseId}/lessons/add")
    public String addLessonToCourse(@PathVariable Integer courseId,
                                    @ModelAttribute("newLesson") BaiHoc newLesson) {
        KhoaHoc khoaHoc = khoaHocService.findById(courseId);
        if (khoaHoc == null) {
            return "redirect:/teacher-courses/" + courseId;
        }
        newLesson.setKhoaHoc(khoaHoc);
        newLesson.setNgayTao(LocalDateTime.now());
        // Có thể thêm logic tự động đặt thuTu (thứ tự) cho bài học
        baiHocService.save(newLesson);
        return "redirect:/teacher-courses/" + courseId;
    }

    // Xóa bài học khỏi khóa học
    @GetMapping("/{courseId}/lessons/delete/{lessonId}")
    public String deleteLessonFromCourse(@PathVariable Integer courseId,
                                         @PathVariable Integer lessonId) {
        baiHocService.deleteById(lessonId);
        return "redirect:/teacher-courses/" + courseId;
    }

    // Hiển thị form chỉnh sửa khóa học
    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable Integer id, Model model) {
        KhoaHoc khoaHoc = khoaHocService.findById(id);
        if (khoaHoc == null) {
            return "redirect:/teacher-courses";
        }
        model.addAttribute("khoaHoc", khoaHoc);
        model.addAttribute("capDoEnums", CapDo.values());
        return "teacher-create-course"; // Sử dụng lại form tạo khóa học để chỉnh sửa
    }
}