package ut.edu.hannah.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "chude")
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChuDe")
    private Integer maChuDe;

    @Column(name = "TenChuDe", nullable = false)
    private String tenChuDe;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "Icon")
    private String icon;

    @ManyToMany(mappedBy = "chuDes")
    private List<KhoaHoc> khoaHocList;

    // Constructor
    public ChuDe() {}

    public ChuDe(Integer maChuDe) {
        this.maChuDe = maChuDe;
    }

    // Getters and Setters
    public Integer getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(Integer maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<KhoaHoc> getKhoaHocList() {
        return khoaHocList;
    }

    public void setKhoaHocList(List<KhoaHoc> khoaHocList) {
        this.khoaHocList = khoaHocList;
    }
}