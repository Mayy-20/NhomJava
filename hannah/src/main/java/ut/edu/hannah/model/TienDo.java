package ut.edu.hannah.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tiendo")
public class TienDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTienDo")
    private Integer maTienDo;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc", nullable = false)
    private BaiHoc baiHoc;

    @Column(name = "PhanTram")
    private BigDecimal phanTram ;

    @Column(name = "ThoiGianHoc")
    private Integer thoiGianHoc = 0;

    @Column(name = "HoanThanh")
    private Boolean hoanThanh = false;

    @Column(name = "LanCuoiHoc")
    private LocalDateTime lanCuoiHoc;

    // Constructor
    public TienDo() {}

    public TienDo(NguoiDung nguoiDung, KhoaHoc khoaHoc, BaiHoc baiHoc) {
        this.nguoiDung = nguoiDung;
        this.khoaHoc = khoaHoc;
        this.baiHoc = baiHoc;
        this.lanCuoiHoc = LocalDateTime.now();
    }

    public Integer getMaTienDo() {
        return maTienDo;
    }

    public void setMaTienDo(Integer maTienDo) {
        this.maTienDo = maTienDo;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public BaiHoc getBaiHoc() {
        return baiHoc;
    }

    public void setBaiHoc(BaiHoc baiHoc) {
        this.baiHoc = baiHoc;
    }

    public BigDecimal getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(BigDecimal phanTram) {
        this.phanTram = phanTram;
    }

    public Integer getThoiGianHoc() {
        return thoiGianHoc;
    }

    public void setThoiGianHoc(Integer thoiGianHoc) {
        this.thoiGianHoc = thoiGianHoc;
    }

    public Boolean getHoanThanh() {
        return hoanThanh;
    }

    public void setHoanThanh(Boolean hoanThanh) {
        this.hoanThanh = hoanThanh;
    }

    public LocalDateTime getLanCuoiHoc() {
        return lanCuoiHoc;
    }

    public void setLanCuoiHoc(LocalDateTime lanCuoiHoc) {
        this.lanCuoiHoc = lanCuoiHoc;
    }
 
    
}