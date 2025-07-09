package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "baihoc")
public class BaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiHoc")
    private Integer maBaiHoc;

    @Column(name = "TenBaiHoc", nullable = false)
    private String tenBaiHoc;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc maKhoaHoc;

    @Column(name = "ThuTu", nullable = false)
    private Integer thuTu;

    @Column(name = "VideoURL")
    private String videoURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "CapDo", nullable = false)
    private CapDo capDo;

    @Column(name = "ThoiLuong")
    private String thoiLuong;

    public enum CapDo {
        CoBan, TrungCap, NangCao
    }
}