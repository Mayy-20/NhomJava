package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "baidang")
public class BaiDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiDang")
    private Integer maBaiDang;

    @Column(name = "TieuDe", nullable = false)
    private String tieuDe;

    @Column(name = "NoiDung", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "MaTacGia", nullable = false)
    private NguoiDung maTacGia;

    @ManyToOne
    @JoinColumn(name = "MaChuDe")
    private ChuDe maChuDe;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc")
    private BaiHoc maBaiHoc;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable = false)
    private TrangThai trangThai;

    @Column(name = "SoBaoCao")
    private Integer soBaoCao;

    @Column(name = "LuotXem")
    private Integer luotXem;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    public enum TrangThai {
        DaDuyet, ChoDuyet, An
    }
}