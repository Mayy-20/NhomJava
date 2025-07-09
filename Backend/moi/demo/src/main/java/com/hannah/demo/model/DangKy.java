package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dangky")
public class DangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDangKy")
    private Integer maDangKy;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung maNguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc maKhoaHoc;

    @Column(name = "NgayDangKy", nullable = false)
    private LocalDateTime ngayDangKy;
}