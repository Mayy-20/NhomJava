package ut.edu.hannah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "khoahoc_chude")
public class KhoaHocChuDe {

    @EmbeddedId
    private KhoaHocChuDeId id;

    @ManyToOne
    @MapsId("maKhoaHoc")
    @JoinColumn(name = "MaKhoaHoc")
    private KhoaHoc khoaHoc;

    @ManyToOne
    @MapsId("maChuDe")
    @JoinColumn(name = "MaChuDe")
    private ChuDe chuDe;

    // Constructor đầy đủ
    public KhoaHocChuDe(KhoaHoc khoaHoc, ChuDe chuDe) {
        this.khoaHoc = khoaHoc;
        this.chuDe = chuDe;
        this.id = new KhoaHocChuDeId(khoaHoc.getMaKhoaHoc(), chuDe.getMaChuDe());
    }

    // Constructor mặc định
    public KhoaHocChuDe() {
    }

    // Getter và Setter
    public KhoaHocChuDeId getId() {
        return id;
    }

    public void setId(KhoaHocChuDeId id) {
        this.id = id;
    }

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

    // Embedded ID class
    @Embeddable
    public static class KhoaHocChuDeId {
        private Integer maKhoaHoc;
        private Integer maChuDe;

        public KhoaHocChuDeId() {
        }

        public KhoaHocChuDeId(Integer maKhoaHoc, Integer maChuDe) {
            this.maKhoaHoc = maKhoaHoc;
            this.maChuDe = maChuDe;
        }

        public Integer getMaKhoaHoc() {
            return maKhoaHoc;
        }

        public void setMaKhoaHoc(Integer maKhoaHoc) {
            this.maKhoaHoc = maKhoaHoc;
        }

        public Integer getMaChuDe() {
            return maChuDe;
        }

        public void setMaChuDe(Integer maChuDe) {
            this.maChuDe = maChuDe;
        }
    }
}