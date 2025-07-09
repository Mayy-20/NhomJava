package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chude")
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChuDe")
    private Integer maChuDe;

    @Column(name = "TenChuDe", nullable = false)
    private String tenChuDe;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "Icon")
    private String icon;
}