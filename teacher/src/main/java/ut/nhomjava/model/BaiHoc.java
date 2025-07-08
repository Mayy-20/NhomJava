package ut.nhomjava.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "baihoc")
public class BaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiHoc")
    private Integer maBaiHoc;

    @Column(name = "TieuDe", nullable = false)
    private String tieuDe;

    @Column(name = "NoiDung")
    private String noiDung;

    @Column(name = "LinkVideo")
    private String linkVideo;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    @Column(name = "ThuTu")
    private Integer thuTu; // Thứ tự bài học trong khóa học

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    // Constructors
    public BaiHoc() {
        this.ngayTao = LocalDateTime.now();
    }

    public BaiHoc(String tieuDe, String noiDung, String linkVideo, KhoaHoc khoaHoc, Integer thuTu) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.linkVideo = linkVideo;
        this.khoaHoc = khoaHoc;
        this.thuTu = thuTu;
        this.ngayTao = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getMaBaiHoc() {
        return maBaiHoc;
    }

    public void setMaBaiHoc(Integer maBaiHoc) {
        this.maBaiHoc = maBaiHoc;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public Integer getThuTu() {
        return thuTu;
    }

    public void setThuTu(Integer thuTu) {
        this.thuTu = thuTu;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}