package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "chude")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChuDe")
    private Integer maChuDe;
    
    @Column(name = "TenChuDe", nullable = false, length = 100)
    private String tenChuDe;
    
    @Column(name = "MoTa", columnDefinition = "TEXT")
    private String moTa;
    
    @Column(name = "Icon", length = 50)
    private String icon;
}
