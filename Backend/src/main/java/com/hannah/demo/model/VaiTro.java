package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vaitro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaVaiTro")
    private Integer maVaiTro;
    
    @Column(name = "TenVaiTro", nullable = false, unique = true, length = 50)
    private String tenVaiTro;
    
    @Column(name = "MoTa", nullable = false, columnDefinition = "TEXT")
    private String moTa;
}
