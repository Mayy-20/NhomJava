package ut.edu.hannah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loaitailieu")
public class LoaiTaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maLoaiTaiLieu;

    @Column(nullable = false)
    private String tenLoai;

    @Column(columnDefinition = "TEXT")
    private String moTa = "";

    // Constructor đầy đủ
    public LoaiTaiLieu(Integer maLoaiTaiLieu, String tenLoai, String moTa) {
        this.maLoaiTaiLieu = maLoaiTaiLieu;
        this.tenLoai = tenLoai;
        this.moTa = moTa != null ? moTa : "";
    }

    // Constructor mặc định
    public LoaiTaiLieu() {
    }

    // Getter và Setter
    public Integer getMaLoaiTaiLieu() {
        return maLoaiTaiLieu;
    }

    public void setMaLoaiTaiLieu(Integer maLoaiTaiLieu) {
        this.maLoaiTaiLieu = maLoaiTaiLieu;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa != null ? moTa : "";
    }
}