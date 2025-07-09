package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "khoahoc")
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoaHoc")
    private Integer maKhoaHoc;

    @Column(name = "TenKhoaHoc", nullable = false, unique = true)
    private String tenKhoaHoc;

    @Column(name = "MoTa")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "MaGiangVien", nullable = false)
    private NguoiDung maGiangVien;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "MienPhi", nullable = false)
    private Boolean mienPhi;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable = false)
    @Convert(converter = TrangThaiKhoaHocConverter.class)
    private TrangThai trangThai;

    @Column(name = "DanhGiaTB")
    private Float danhGiaTB;

    @Column(name = "SoLuongHocVien")
    private Integer soLuongHocVien;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    @ManyToMany
    @JoinTable(
        name = "khoahoc_chude",
        joinColumns = @JoinColumn(name = "MaKhoaHoc"),
        inverseJoinColumns = @JoinColumn(name = "MaChuDe")
    )
    private Set<ChuDe> chuDe;

    public enum TrangThai {
        ACTIVE("HoatDong"),
        PENDING("ChoDuyet"),
        HIDDEN("An");

        private final String dbValue;

        TrangThai(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }
    }
}