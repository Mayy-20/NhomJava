package ut.edu.hannah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "khoahoc_chude")
public class KhoaHocChuDe {
    @Id
    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    @Id
    @ManyToOne
    @JoinColumn(name = "MaChuDe", nullable = false)
    private ChuDe chuDe;

    // Constructor
    public KhoaHocChuDe() {}
 
    public KhoaHocChuDe(KhoaHoc khoaHoc, ChuDe chuDe) {
        this.khoaHoc = khoaHoc;
        this.chuDe = chuDe;
    }

    // Getters and Setters
    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }
    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }
    public ChuDe getChuDe() {
        return chuDe;
    }
    public void setChuDe(ChuDe chuDe) {
        this.chuDe = chuDe;
    }
   
}