package ut.edu.hannah.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "nguoidung")
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "TenDangNhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    @Column(name = "MaVaiTro", nullable = false)
    private Integer maVaiTro;

    @Column(name = "AnhDaiDien")
    private String anhDaiDien;

    @Column(name = "DienThoai")
    private String dienThoai;

    @Column(name = "GioiThieu")
    private String gioiThieu;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable = false)
    private TrangThai trangThai;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    public enum TrangThai {
        Active, Inactive, Banned
    }
}