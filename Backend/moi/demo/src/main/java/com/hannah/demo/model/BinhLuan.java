package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "binhluan")
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBinhLuan")
    private Integer maBinhLuan;

    @Column(name = "NoiDung", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "MaBaiDang", nullable = false)
    private BaiDang maBaiDang;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung maNguoiDung;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;
}