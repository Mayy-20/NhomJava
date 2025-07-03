package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dangky")
public class DangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDangKy")
    private Integer maDangKy;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    @Column(name = "NgayDangKy")
    private LocalDateTime ngayDangKy = LocalDateTime.now();

    // Constructor
    public DangKy() {}
    public DangKy(NguoiDung nguoiDung, KhoaHoc khoaHoc) {
        this.nguoiDung = nguoiDung;
        this.khoaHoc = khoaHoc;
    }

    // Getters and Setters
    public Integer getMaDangKy() {
        return maDangKy;
    }
    public void setMaDangKy(Integer maDangKy) {
        this.maDangKy = maDangKy;
    }
    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }
    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }
    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }
    public LocalDateTime getNgayDangKy() {
        return ngayDangKy;
    }
    public void setNgayDangKy(LocalDateTime ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
   
}