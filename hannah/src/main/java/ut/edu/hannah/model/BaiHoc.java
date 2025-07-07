package ut.edu.hannah.model;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    @Column(name = "ThuTu", nullable = false)
    private Integer thuTu;

    @Column(name = "VideoURL")
    private String videoURL;

    @Column(name = "MoTa", nullable = false)
    private String moTa;

    @Enumerated(EnumType.STRING)
    @Column(name = "CapDo")
    private CapDo capDo = CapDo.CoBan;

    @Column(name = "ThoiLuong")
    private String thoiLuong;
    @OneToMany(mappedBy = "baiHoc")
    private List<TaiLieu> taiLieuList;
    @OneToMany(mappedBy = "baiHoc")
private List<TienDo> tienDoList;
    public enum CapDo {
        CoBan, TrungCap, NangCao
    }

    // Constructor
    public BaiHoc() {}
    public BaiHoc(Integer maBaiHoc, String tenBaiHoc, KhoaHoc khoaHoc, Integer thuTu, String videoURL, String moTa,
            CapDo capDo, String thoiLuong, List<TaiLieu> taiLieuList, List<TienDo> tienDoList) {
        this.maBaiHoc = maBaiHoc;
        this.tenBaiHoc = tenBaiHoc;
        this.khoaHoc = khoaHoc;
        this.thuTu = thuTu;
        this.videoURL = videoURL;
        this.moTa = moTa;
        this.capDo = capDo;
        this.thoiLuong = thoiLuong;
        this.taiLieuList = taiLieuList;
        this.tienDoList = tienDoList;
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
    public String getVideoURL() {
        return videoURL;
    }
    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
    public List<TaiLieu> getTaiLieuList() {
        return taiLieuList;
    }
    public void setTaiLieuList(List<TaiLieu> taiLieuList) {
        this.taiLieuList = taiLieuList;
    }
    public List<TienDo> getTienDoList() {
        return tienDoList;
    }
    public void setTienDoList(List<TienDo> tienDoList) {
        this.tienDoList = tienDoList;
    }
    
}