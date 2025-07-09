package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tiendo")
public class TienDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTienDo")
    private Integer maTienDo;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung maNguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc maKhoaHoc;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc", nullable = false)
    private BaiHoc maBaiHoc;

    @Column(name = "PhanTram")
    private Float phanTram;

    @Column(name = "ThoiGianHoc")
    private Integer thoiGianHoc;

    @Column(name = "HoanThanh")
    private Boolean hoanThanh;

    @Column(name = "LanCuoiHoc")
    private LocalDateTime lanCuoiHoc;
}