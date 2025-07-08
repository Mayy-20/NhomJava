package ut.nhomjava.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "baihoc") // Tên bảng trong CSDL
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiHoc") // Tên cột ID trong CSDL
    private Long id; // Kiểu Long như trong LessonController

    @Column(name = "TieuDe", nullable = false)
    private String title;

    @Column(name = "NoiDung")
    private String content;

    @Column(name = "VideoURL")
    private String videoUrl;

    @Column(name = "TaiLieuDinhKem")
    private String attachments; // Có thể là JSON hoặc đường dẫn phân cách bằng dấu phẩy

    @Column(name = "NgayTao")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhoaHoc", nullable = false) // Khóa ngoại trỏ về bảng khoahoc
    private Course course;

    public Lesson() {
        this.createdAt = LocalDateTime.now();
    }

    public Lesson(String title, String content, String videoUrl, String attachments, Course course) {
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.attachments = attachments;
        this.course = course;
        this.createdAt = LocalDateTime.now();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}