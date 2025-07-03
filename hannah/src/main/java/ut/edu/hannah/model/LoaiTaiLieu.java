package ut.edu.hannah.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "loaitailieu")
public class LoaiTaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoaiTaiLieu")
    private Integer maLoaiTaiLieu;

    @Column(name = "TenLoai", nullable = false, unique = true)
    private String tenLoai;

    @Column(name = "MoTa")
    private String moTa;
    @OneToMany(mappedBy = "loaiTaiLieu")
    private List<TaiLieu> taiLieuList;

    // Constructor
    public LoaiTaiLieu() {}

    // Getters and Setters
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
        this.moTa = moTa;
    }
    public List<TaiLieu> getTaiLieuList() {
        return taiLieuList;
    }
    public void setTaiLieuList(List<TaiLieu> taiLieuList) {
        this.taiLieuList = taiLieuList;
    }
}