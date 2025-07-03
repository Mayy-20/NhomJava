package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "nguoidung")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;
    
    @Column(name = "TenDangNhap", nullable = false, unique = true, length = 50)
    private String tenDangNhap;
    
    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "MatKhau", nullable = false, length = 255)
    private String matKhau;
    
    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;
    
    @Column(name = "MaVaiTro", nullable = false)
    private Integer maVaiTro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaVaiTro", insertable = false, updatable = false)
    private VaiTro vaiTro;
    
    @Column(name = "AnhDaiDien", length = 255)
    private String anhDaiDien;
    
    @Column(name = "DienThoai", length = 20)
    private String dienThoai;
    
    @Column(name = "GioiThieu", columnDefinition = "TEXT")
    private String gioiThieu;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThaiNguoiDung trangThai = TrangThaiNguoiDung.Active;
    
    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();
    
    public enum TrangThaiNguoiDung {
        Active, Inactive, Banned
    }
}
