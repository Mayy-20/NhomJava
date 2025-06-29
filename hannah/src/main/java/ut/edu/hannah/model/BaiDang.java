package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "baidang")
public class BaiDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiDang")
    private Integer maBaiDang;

    @Column(name = "TieuDe", nullable = false)
    private String tieuDe;

    @Column(name = "NoiDung", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "MaTacGia", nullable = false)
    private NguoiDung maTacGia;

    @ManyToOne
    @JoinColumn(name = "MaChuDe")
    private ChuDe chuDe;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc")
    private BaiHoc baiHoc;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.ChoDuyet;

    @Column(name = "SoBaoCao")
    private Integer soBaoCao = 0;

    @Column(name = "LuotXem")
    private Integer luotXem = 0;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThai {
        DaDuyet, ChoDuyet, An
    }

    // Giả lập các trường không có trong CSDL
    private Integer luotThich = 0;
    private Integer luotBinhLuan = 0;
    private Integer luotChiaSe = 0;
    private String hinhAnh;

    public BaiDang() {}
    public BaiDang(String tieuDe, String noiDung, NguoiDung maTacGia) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.maTacGia = maTacGia;
    }
    public Integer getMaBaiDang() {
        return maBaiDang;
    }
    public void setMaBaiDang(Integer maBaiDang) {
        this.maBaiDang = maBaiDang;
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
    public NguoiDung getMaTacGia() {
        return maTacGia;
    }
    public void setMaTacGia(NguoiDung maTacGia) {
        this.maTacGia = maTacGia;
    }
    public ChuDe getChuDe() {
        return chuDe;
    }
    public void setChuDe(ChuDe chuDe) {
        this.chuDe = chuDe;
    }
    public BaiHoc getBaiHoc() {
        return baiHoc;
    }
    public void setBaiHoc(BaiHoc baiHoc) {
        this.baiHoc = baiHoc;
    }
    public TrangThai getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }
    public Integer getSoBaoCao() {
        return soBaoCao;
    }
    public void setSoBaoCao(Integer soBaoCao) {
        this.soBaoCao = soBaoCao;
    }
    public Integer getLuotXem() {
        return luotXem;
    }
    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }
    public LocalDateTime getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
    public Integer getLuotThich() {
        return luotThich;
    }
    public void setLuotThich(Integer luotThich) {
        this.luotThich = luotThich;
    }
    public Integer getLuotBinhLuan() {
        return luotBinhLuan;
    }
    public void setLuotBinhLuan(Integer luotBinhLuan) {
        this.luotBinhLuan = luotBinhLuan;
    }
    public Integer getLuotChiaSe() {
        return luotChiaSe;
    }
    public void setLuotChiaSe(Integer luotChiaSe) {
        this.luotChiaSe = luotChiaSe;
    }
    public String getHinhAnh() {
        return hinhAnh;
    }
    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}