package ut.edu.hannah.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "khoahoc")
@Data
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoaHoc")
    private Integer maKhoaHoc;

    @Column(name = "TenKhoaHoc", nullable = false, unique = true)
    private String tenKhoaHoc;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "MaGiangVien", nullable = false)
    private Integer maGiangVien;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "MienPhi")
    private Boolean mienPhi;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable = false)
    private TrangThai trangThai;

    @Column(name = "DanhGiaTB")
    private Float danhGiaTB;

    @Column(name = "SoLuongHocVien")
    private Integer soLuongHocVien;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    public enum TrangThai {
        HoatDong, ChoDuyet, An
    }
}