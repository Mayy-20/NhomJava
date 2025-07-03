package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tailieu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTaiLieu")
    private Integer maTaiLieu;
    
    @Column(name = "TenTaiLieu", nullable = false, length = 300)
    private String tenTaiLieu;
    
    @Column(name = "MaLoaiTaiLieu", nullable = false)
    private Integer maLoaiTaiLieu;
    
    @Column(name = "MaBaiHoc", nullable = false)
    private Integer maBaiHoc;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBaiHoc", insertable = false, updatable = false)
    private BaiHoc baiHoc;
    
    @Column(name = "MaTacGia", nullable = false)
    private Integer maTacGia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTacGia", insertable = false, updatable = false)
    private NguoiDung tacGia;
    
    @Column(name = "DuongDan", length = 500)
    private String duongDan;
    
    @Column(name = "KichThuoc")
    private Long kichThuoc;
    
    @Column(name = "LuotTai")
    private Integer luotTai = 0;
    
    @Column(name = "DanhGia")
    private Float danhGia = 0.0f;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThaiTaiLieu trangThai = TrangThaiTaiLieu.ChoDuyet;
    
    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();
    
    public enum TrangThaiTaiLieu {
        DaDuyet, ChoDuyet, TuChoi
    }
}
