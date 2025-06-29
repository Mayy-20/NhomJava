package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tailieu")
public class TaiLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTaiLieu;

    @Column(nullable = false)
    private String tenTaiLieu;

    @ManyToOne
    @JoinColumn(name = "maLoaiTaiLieu", nullable = false)
    private LoaiTaiLieu loaiTaiLieu;

    @ManyToOne
    @JoinColumn(name = "maBaiHoc", nullable = false)
    private BaiHoc baiHoc;

    @ManyToOne
    @JoinColumn(name = "maTacGia", nullable = false)
    private NguoiDung tacGia;

    private String duongDan;

    private Long kichThuoc;

    private Integer luotTai;

    private Float danhGia;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    @Column(updatable = false)
    private LocalDateTime ngayTao;

    private String format;

    @ElementCollection
    private List<String> tags;

    @Transient
    private String tenLoai;

    public enum TrangThai {
        DaDuyet, ChoDuyet, TuChoi
    }

    // Constructor đầy đủ
    public TaiLieu(Integer maTaiLieu, String tenTaiLieu, LoaiTaiLieu loaiTaiLieu, BaiHoc baiHoc, NguoiDung tacGia,
                   String duongDan, Long kichThuoc, Integer luotTai, Float danhGia, TrangThai trangThai,
                   LocalDateTime ngayTao, String format, List<String> tags, String tenLoai) {
        this.maTaiLieu = maTaiLieu;
        this.tenTaiLieu = tenTaiLieu;
        this.loaiTaiLieu = loaiTaiLieu;
        this.baiHoc = baiHoc;
        this.tacGia = tacGia;
        this.duongDan = duongDan;
        this.kichThuoc = kichThuoc;
        this.luotTai = luotTai;
        this.danhGia = danhGia;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.format = format;
        this.tags = tags;
        this.tenLoai = tenLoai;
    }

    // Constructor mặc định
    public TaiLieu() {
    }

    // Getter và Setter
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}