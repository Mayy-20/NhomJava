package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tailieu")
public class TaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTaiLieu")
    private Integer maTaiLieu;

    @Column(name = "TenTaiLieu", nullable = false)
    private String tenTaiLieu;

    @ManyToOne
    @JoinColumn(name = "MaLoaiTaiLieu", nullable = false)
    private LoaiTaiLieu loaiTaiLieu;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc", nullable = false)
    private BaiHoc baiHoc;

    @ManyToOne
    @JoinColumn(name = "MaTacGia", nullable = false)
    private NguoiDung tacGia;

    @Column(name = "DuongDan")
    private String duongDan;

    @Column(name = "KichThuoc")
    private Long kichThuoc;

    @Column(name = "LuotTai")
    private Integer luotTai = 0;

    @Column(name = "DanhGia")
    private Float danhGia = 0f;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.ChoDuyet;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThai {
        DaDuyet, ChoDuyet, TuChoi
    }

    // Constructor
    public TaiLieu() {}

    // Getters and Setters
    public Integer getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(Integer maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTenTaiLieu() {
        return tenTaiLieu;
    }

    public void setTenTaiLieu(String tenTaiLieu) {
        this.tenTaiLieu = tenTaiLieu;
    }

    public LoaiTaiLieu getLoaiTaiLieu() {
        return loaiTaiLieu;
    }

    public void setLoaiTaiLieu(LoaiTaiLieu loaiTaiLieu) {
        this.loaiTaiLieu = loaiTaiLieu;
    }

    public BaiHoc getBaiHoc() {
        return baiHoc;
    }

    public void setBaiHoc(BaiHoc baiHoc) {
        this.baiHoc = baiHoc;
    }

    public NguoiDung getTacGia() {
        return tacGia;
    }

    public void setTacGia(NguoiDung tacGia) {
        this.tacGia = tacGia;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public Long getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(Long kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public Integer getLuotTai() {
        return luotTai;
    }

    public void setLuotTai(Integer luotTai) {
        this.luotTai = luotTai;
    }

    public Float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(Float danhGia) {
        this.danhGia = danhGia;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}