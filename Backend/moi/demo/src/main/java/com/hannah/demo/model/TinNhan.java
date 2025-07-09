package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tinnhan")
public class TinNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTinNhan")
    private Integer maTinNhan;

    @ManyToOne
    @JoinColumn(name = "MaPhien", nullable = false)
    private PhienChat maPhien;

    @Enumerated(EnumType.STRING)
    @Column(name = "NguoiGui", nullable = false)
    private NguoiGui nguoiGui;

    @Column(name = "NoiDung")
    private String noiDung;

    @Column(name = "ThoiGian", nullable = false)
    private LocalDateTime thoiGian;

    public enum NguoiGui {
        AI, User
    }
}