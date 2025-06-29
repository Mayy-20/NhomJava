package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "baidang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiDang")
    private Integer maBaiDang;
    
    @Column(name = "TieuDe", nullable = false, length = 300)
    private String tieuDe;
    
    @Column(name = "NoiDung", nullable = false, columnDefinition = "TEXT")
    private String noiDung;
    
    @Column(name = "MaTacGia", nullable = false)
    private Integer maTacGia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTacGia", insertable = false, updatable = false)
    private NguoiDung tacGia;
    
    @Column(name = "MaChuDe")
    private Integer maChuDe;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChuDe", insertable = false, updatable = false)
    private ChuDe chuDe;
    
    @Column(name = "MaBaiHoc")
    private Integer maBaiHoc;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBaiHoc", insertable = false, updatable = false)
    private BaiHoc baiHoc;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThaiBaiDang trangThai = TrangThaiBaiDang.ChoDuyet;
    
    @Column(name = "SoBaoCao")
    private Integer soBaoCao = 0;
    
    @Column(name = "LuotXem")
    private Integer luotXem = 0;
    
    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();
    
    public enum TrangThaiBaiDang {
        DaDuyet, ChoDuyet, An
    }
}
