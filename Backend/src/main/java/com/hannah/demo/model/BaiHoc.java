package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "baihoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiHoc")
    private Integer maBaiHoc;
    
    @Column(name = "TenBaiHoc", nullable = false, length = 200)
    private String tenBaiHoc;
    
    @Column(name = "MaKhoaHoc", nullable = false)
    private Integer maKhoaHoc;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhoaHoc", insertable = false, updatable = false)
    private KhoaHoc khoaHoc;
    
    @Column(name = "ThuTu", nullable = false)
    private Integer thuTu;
    
    @Column(name = "VideoURL", length = 255)
    private String videoURL;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CapDo")
    private CapDoBaiHoc capDo = CapDoBaiHoc.CoBan;
    
    @Column(name = "ThoiLuong", length = 50)
    private String thoiLuong;
    
    public enum CapDoBaiHoc {
        CoBan, TrungCap, NangCao
    }
}
