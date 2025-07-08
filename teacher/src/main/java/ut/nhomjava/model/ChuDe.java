package ut.nhomjava.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "chude")
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChuDe")
    private Integer maChuDe;

    @Column(name = "TenChuDe", nullable = false, unique = true)
    private String tenChuDe;

    @ManyToMany(mappedBy = "chuDes")
    private Set<KhoaHoc> khoaHocs;

    // Constructors
    public ChuDe() {}

    public ChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
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

    public Set<KhoaHoc> getKhoaHocs() {
        return khoaHocs;
    }

    public void setKhoaHocs(Set<KhoaHoc> khoaHocs) {
        this.khoaHocs = khoaHocs;
    }
}