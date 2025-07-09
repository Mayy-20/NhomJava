package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "loaitailieu")
public class LoaiTaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoaiTaiLieu")
    private Integer maLoaiTaiLieu;

    @Column(name = "TenLoai", nullable = false, unique = true)
    private String tenLoai;

    @Column(name = "MoTa")
    private String moTa;
}