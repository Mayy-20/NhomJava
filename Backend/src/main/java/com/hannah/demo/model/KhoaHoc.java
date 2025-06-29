package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "khoahoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoaHoc")
    private Integer maKhoaHoc;
    
    @Column(name = "TenKhoaHoc", nullable = false, length = 200)
    private String tenKhoaHoc;
    
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String moTa;
    
    @Column(name = "MaGiangVien", nullable = false)
    private Integer maGiangVien;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaGiangVien", insertable = false, updatable = false)
    private NguoiDung giangVien;
    
    @Column(name = "HinhAnh", length = 255)
    private String hinhAnh;
    
    @Column(name = "MienPhi")
    private Boolean mienPhi = true;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThaiKhoaHoc trangThai = TrangThaiKhoaHoc.HoatDong;
    
    @Column(name = "DanhGiaTB")
    private Float danhGiaTB = 0.0f;
    
    @Column(name = "SoLuongHocVien")
    private Integer soLuongHocVien = 0;
    
    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();
    
    public enum TrangThaiKhoaHoc {
        HoatDong, ChoDuyet, An
    }
}
