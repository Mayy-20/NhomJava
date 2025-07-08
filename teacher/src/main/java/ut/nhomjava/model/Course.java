package ut.nhomjava.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "khoahoc") // Tên bảng vẫn là khoahoc
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoaHoc") // Tên cột ID trong CSDL
    private Long id; // Đổi sang Long để phù hợp với LessonController

    @Column(name = "TenKhoaHoc", nullable = false)
    private String title; // Đổi tên thuộc tính từ tenKhoaHoc

    @Column(name = "MoTa")
    private String description; // Đổi tên thuộc tính từ moTa

    @Column(name = "HinhAnh")
    private String imageUrl; // Đổi tên thuộc tính từ hinhAnh

    @Column(name = "SoLuongBaiHoc")
    private Integer lessons; // Đổi tên thuộc tính từ soLuongBaiHoc

    @Column(name = "DanhGiaTrungBinh")
    private Double rating; // Đổi tên thuộc tính từ danhGiaTrungBinh

    @Column(name = "TrangThai")
    private String status; // Đổi tên thuộc tính từ trangThai

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessonList = new ArrayList<>(); // Mối quan hệ One-to-Many với Lesson

    public Course() {}

    // Constructor với các thuộc tính cơ bản
    public Course(String title, String description, String imageUrl, Integer lessons, Double rating, String status) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.lessons = lessons;
        this.rating = rating;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLessons() {
        return lessons;
    }

    public void setLessons(Integer lessons) {
        this.lessons = lessons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    // Phương thức tiện ích để thêm bài học
    public void addLesson(Lesson lesson) {
        lessonList.add(lesson);
        lesson.setCourse(this);
    }

    // Phương thức tiện ích để xóa bài học
    public void removeLesson(Lesson lesson) {
        lessonList.remove(lesson);
        lesson.setCourse(null);
    }
}