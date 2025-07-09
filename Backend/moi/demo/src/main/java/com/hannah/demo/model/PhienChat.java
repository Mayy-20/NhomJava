package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "phienchat")
public class PhienChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhien")
    private Integer maPhien;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung maNguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc")
    private BaiHoc maBaiHoc;

    @Column(name = "BatDau", nullable = false)
    private LocalDateTime batDau;
}