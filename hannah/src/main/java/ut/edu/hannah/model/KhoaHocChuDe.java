package ut.edu.hannah.model;


import jakarta.persistence.*;

@Entity
@Table(name = "khoahoc_chude")
public class KhoaHocChuDe {
    @Id
    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc")
    private KhoaHoc khoaHoc;

    @Id
    @Column(name = "MaChuDe")
    private Integer maChuDe;

    public KhoaHoc getKhoaHoc() { return khoaHoc; }
    public void setKhoaHoc(KhoaHoc khoaHoc) { this.khoaHoc = khoaHoc; }
    public Integer getMaChuDe() { return maChuDe; }
    public void setMaChuDe(Integer maChuDe) { this.maChuDe = maChuDe; }
}