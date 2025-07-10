package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "khoahoc")
@Data
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKhoaHoc;

    @Column(unique = true, nullable = false)
    private String tenKhoaHoc;

    private String moTa;

    @ManyToOne
    @JoinColumn(name = "maGiangVien", nullable = false)
    private NguoiDung giangVien;

    private String hinhAnh;
    private Boolean mienPhi = true;

    @Enumerated(EnumType.STRING)
    private TrangThaiKhoaHoc trangThai = TrangThaiKhoaHoc.HoatDong;

    private Float danhGiaTB = 0.0f;
    private Integer soLuongHocVien = 0;

    @Column(updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
        name = "khoahoc_chude",
        joinColumns = @JoinColumn(name = "maKhoaHoc"),
        inverseJoinColumns = @JoinColumn(name = "maChuDe")
    )
    private List<ChuDe> chuDe;

    public enum TrangThaiKhoaHoc {
        HoatDong, ChoDuyet, An
    }
}