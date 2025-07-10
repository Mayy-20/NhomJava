package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "nguoidung")
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNguoiDung;

    @Column(unique = true, nullable = false)
    private String tenDangNhap;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String matKhau;

    @Column(nullable = false)
    private String hoTen;

    @ManyToOne
    @JoinColumn(name = "maVaiTro", nullable = false)
    private VaiTro vaiTro;

    private String anhDaiDien;
    private String dienThoai;
    private String gioiThieu;

    @Enumerated(EnumType.STRING)
    private TrangThaiNguoiDung trangThai;

    @Column(updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThaiNguoiDung {
        Active, Inactive, Banned
    }
}