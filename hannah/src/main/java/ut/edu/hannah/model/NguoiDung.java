package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Lớp này đại diện cho người dùng trong hệ thống
@Entity
@Table(name = "nguoidung")
public class NguoiDung {
    // ID duy nhất của người dùng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    // Tên đăng nhập, duy nhất
    @Column(name = "TenDangNhap", nullable = false, unique = true)
    private String tenDangNhap;

    // Email, duy nhất
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    // Mật khẩu
    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    // Họ và tên
    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    // Ảnh đại diện
    @Column(name = "AnhDaiDien")
    private String anhDaiDien;

    // Vai trò của người dùng
    @Column(name = "MaVaiTro", nullable = false)
    private Integer maVaiTro;

    // Trạng thái người dùng
    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.Active;

    // Ngày tạo tài khoản
    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThai {
        Active, Inactive, Banned
    }

    // Constructor mặc định
    public NguoiDung() {
    }

    // Constructor cơ bản
    public NguoiDung(String tenDangNhap, String email, String matKhau, String hoTen, Integer maVaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.email = email;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.maVaiTro = maVaiTro;
    }

    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public Integer getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(Integer maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    // Getter và Setter
  
}