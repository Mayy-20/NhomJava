package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "binhluan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBinhLuan")
    private Integer maBinhLuan;
    
    @Column(name = "NoiDung", nullable = false, columnDefinition = "TEXT")
    private String noiDung;
    
    @Column(name = "MaBaiDang", nullable = false)
    private Integer maBaiDang;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBaiDang", insertable = false, updatable = false)
    private BaiDang baiDang;
    
    @Column(name = "MaNguoiDung", nullable = false)
    private Integer maNguoiDung;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    
    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();
}
