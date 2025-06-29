package ut.edu.hannah.model;

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
    private String moTa = "";

    @Column(name = "Icon")
    private String icon;

    // Constructor đầy đủ
    public ChuDe(Integer maChuDe, String tenChuDe, String moTa, String icon) {
        this.maChuDe = maChuDe;
        this.tenChuDe = tenChuDe;
        this.moTa = moTa != null ? moTa : "";
        this.icon = icon;
    }

    // Constructor mặc định
    public ChuDe() {
    }

    // Getter và Setter
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
        this.moTa = moTa != null ? moTa : "";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}