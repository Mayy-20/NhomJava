package ut.edu.hannah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "baihoc")
public class BaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiHoc")
    private Integer maBaiHoc;

    @Column(name = "TenBaiHoc", nullable = false)
    private String tenBaiHoc;

    @Column(name = "ThuTu", nullable = false)
    private Integer thuTu;

    @Column(name = "VideoURL")
    private String videoURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "CapDo")
    private CapDo capDo = CapDo.CoBan;

    @Column(name = "ThoiLuong")
    private String thoiLuong;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    public enum CapDo {
        CoBan, TrungCap, NangCao
    }

    public BaiHoc() {}
    public BaiHoc(String tenBaiHoc, Integer thuTu, KhoaHoc khoaHoc) {
        this.tenBaiHoc = tenBaiHoc;
        this.thuTu = thuTu;
        this.khoaHoc = khoaHoc;
    }
    
    // Getters and Setters
    public Integer getMaBaiHoc() {
        return maBaiHoc;
    }
    public void setMaBaiHoc(Integer maBaiHoc) {
        this.maBaiHoc = maBaiHoc;
    }
    public String getTenBaiHoc() {
        return tenBaiHoc;
    }
    public void setTenBaiHoc(String tenBaiHoc) {
        this.tenBaiHoc = tenBaiHoc;
    }
    public Integer getThuTu() {
        return thuTu;
    }
    public void setThuTu(Integer thuTu) {
        this.thuTu = thuTu;
    }
    public String getVideoURL() {
        return videoURL;
    }
    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
    public CapDo getCapDo() {
        return capDo;
    }
    public void setCapDo(CapDo capDo) {
        this.capDo = capDo;
    }
    public String getThoiLuong() {
        return thoiLuong;
    }
    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }
    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }
    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

}