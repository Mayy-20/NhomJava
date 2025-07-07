package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private NguoiDung tacGia;

    @ManyToOne
    @JoinColumn(name = "MaChuDe")
    private ChuDe chuDe;

    @OneToOne
    @JoinColumn(name = "MaBaiHoc", unique = true)
    private BaiHoc baiHoc;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.ChoDuyet;

    @Column(name = "SoBaoCao")
    private Integer soBaoCao = 0;

    @Column(name = "LuotXem")
    private Integer luotXem = 0;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThai {
        DaDuyet, ChoDuyet, An
    }
    @OneToMany(mappedBy = "baiDang")
    private List<BinhLuan> binhLuanList;

    // Constructor
    public BaiDang() {}

    public BaiDang(String tieuDe, String noiDung, NguoiDung tacGia) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.tacGia = tacGia;
    }
    // Getters and Setters
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

    public NguoiDung getTacGia() {
        return tacGia;
    }

    public void setTacGia(NguoiDung tacGia) {
        this.tacGia = tacGia;
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
    public List<BinhLuan> getBinhLuanList() {
        return binhLuanList;
    }
    public void setBinhLuanList(List<BinhLuan> binhLuanList) {
        this.binhLuanList = binhLuanList;
    }
}