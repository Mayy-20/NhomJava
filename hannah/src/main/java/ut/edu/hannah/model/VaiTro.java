package ut.edu.hannah.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "vaitro")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaVaiTro")
    private Integer maVaiTro;

    @Column(name = "TenVaiTro", nullable = false, unique = true)
    private String tenVaiTro;

    @Column(name = "MoTa", nullable = false)
    private String moTa;
    @OneToMany(mappedBy = "vaiTro")
    private List<NguoiDung> nguoiDungList;

    // Constructor
    public VaiTro() {}

    public VaiTro(Integer maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    // Getters and Setters
    public Integer getMaVaiTro() {
        return maVaiTro;
    }
    public void setMaVaiTro(Integer maVaiTro) {
        this.maVaiTro = maVaiTro;
    }
    public String getTenVaiTro() {
        return tenVaiTro;
    }
    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public List<NguoiDung> getNguoiDungList() {
        return nguoiDungList;
    }
    public void setNguoiDungList(List<NguoiDung> nguoiDungList) {
        this.nguoiDungList = nguoiDungList;
    }
}