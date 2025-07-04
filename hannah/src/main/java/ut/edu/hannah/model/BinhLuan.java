package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "binhluan")
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBinhLuan")
    private Integer maBinhLuan;

    @Column(name = "NoiDung", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "MaBaiDang", nullable = false)
    private BaiDang baiDang;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung nguoiDung;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    // Constructor
    public BinhLuan() {}

    public BinhLuan(String noiDung, BaiDang baiDang, NguoiDung nguoiDung) {
        this.noiDung = noiDung;
        this.baiDang = baiDang;
        this.nguoiDung = nguoiDung;
    }

    // Getters and Setters
    public Integer getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(Integer maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public BaiDang getBaiDang() {
        return baiDang;
    }

    public void setBaiDang(BaiDang baiDang) {
        this.baiDang = baiDang;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
    }
}