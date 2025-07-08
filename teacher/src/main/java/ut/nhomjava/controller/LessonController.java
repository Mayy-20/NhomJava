package ut.nhomjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; // Đảm bảo đã import

import ut.nhomjava.model.Course; // Đã chuyển sang package ut.nhomjava.model
import ut.nhomjava.model.Lesson; // Đã chuyển sang package ut.nhomjava.model
import ut.nhomjava.service.CourseService; // Đã chuyển sang package ut.nhomjava.service
import ut.nhomjava.service.LessonService; // Đã chuyển sang package ut.nhomjava.service

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/lesson-editor")
    public String editLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        // Thêm danh sách khóa học vào model để populate dropdown
        model.addAttribute("courses", courseService.findAll());
        return "teacher-lesson-editor"; // Tên file .html trong /templates/
    }

    @PostMapping("/save") // Nên cân nhắc đổi thành /lessons/save hoặc /courses/{courseId}/lessons/save
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson,
                             @RequestParam(value = "videoFile", required = false) MultipartFile videoFile, // videoFile có thể không bắt buộc
                             @RequestParam(value = "attachments", required = false) MultipartFile[] attachments) { // attachments có thể không bắt buộc
        // Bạn sẽ cần logic để lưu các tệp này và gán đường dẫn vào đối tượng Lesson nếu cần.
        // Ví dụ:
        if (videoFile != null && !videoFile.isEmpty()) {
            // Logic lưu file video và set URL cho lesson.setVideoUrl(...)
            // lesson.setVideoUrl("/path/to/videos/" + videoFile.getOriginalFilename());
        }
        if (attachments != null && attachments.length > 0) {
            // Logic lưu các file đính kèm và set đường dẫn cho lesson.setAttachments(...)
            // StringBuilder attachmentPaths = new StringBuilder();
            // for (MultipartFile file : attachments) {
            //     attachmentPaths.append("/path/to/attachments/").append(file.getOriginalFilename()).append(";");
            // }
            // lesson.setAttachments(attachmentPaths.toString());
        }

        // Lưu đối tượng lesson vào cơ sở dữ liệu
        lessonService.save(lesson);

        // Chuyển hướng về trang chi tiết khóa học, truyền kèm courseId
        Long courseId = lesson.getCourse() != null ? lesson.getCourse().getId() : null;
        if (courseId != null) {
            return "redirect:/courses/view?id=" + courseId;
        } else {
            // Trường hợp không có courseId (ví dụ: lỗi), chuyển hướng về trang danh sách khóa học
            return "redirect:/teacher-courses";
        }
    }

    @GetMapping("/courses/{courseId}/add-lesson")
    public String showAddLessonForm(@PathVariable Long courseId, Model model) {
        Lesson lesson = new Lesson();
        // Gán Course cho Lesson ngay khi khởi tạo form để đảm bảo mối quan hệ
        Course course = courseService.findById(courseId);
        if (course != null) {
            lesson.setCourse(course);
            model.addAttribute("lesson", lesson);
            model.addAttribute("course", course); // Thêm course vào model để dùng trong template
            return "teacher-lesson-editor";
        }
        return "redirect:/teacher-courses"; // Hoặc trang lỗi
    }

    @GetMapping("/lessons/edit")
    public String editLessonForm(@RequestParam("id") Long lessonId, @RequestParam("courseId") Long courseId, Model model) {
        Lesson lesson = lessonService.findById(lessonId);
        Course course = courseService.findById(courseId);
        if (lesson != null && course != null) {
            model.addAttribute("lesson", lesson);
            model.addAttribute("course", course); // Thêm course vào model
            return "teacher-lesson-editor";
        }
        return "redirect:/teacher-courses"; // Hoặc trang lỗi
    }

    @GetMapping("/lessons/delete")
    public String deleteLesson(@RequestParam("id") Long lessonId, @RequestParam("courseId") Long courseId) {
        lessonService.deleteById(lessonId);
        return "redirect:/courses/view?id=" + courseId;
    }
}