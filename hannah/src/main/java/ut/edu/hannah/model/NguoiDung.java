package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nguoidung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "TenDangNhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;
@OneToMany(mappedBy = "tacGia")
private List<BaiDang> baiDangList;

@OneToMany(mappedBy = "nguoiDung")
private List<BinhLuan> binhLuanList;

@OneToMany(mappedBy = "nguoiDung")
private List<TienDo> tienDoList;

// @OneToMany(mappedBy = "nguoiDung")
// private List<DangKy> dangKyList;

@OneToMany(mappedBy = "giangVien")
private List<KhoaHoc> khoaHocList;

@OneToMany(mappedBy = "tacGia")
private List<TaiLieu> taiLieuList;
    @ManyToOne
    @JoinColumn(name = "MaVaiTro", nullable = false)
    private VaiTro vaiTro;

    @Column(name = "AnhDaiDien")
    private String anhDaiDien;

    @Column(name = "DienThoai")
    private String dienThoai;

    @Column(name = "GioiThieu")
    private String gioiThieu;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.Active;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThai {
        Active, Inactive, Banned
    }

    // Constructor
    public NguoiDung() {}
    public NguoiDung(Integer maNguoiDung, String tenDangNhap, String email, String matKhau, String hoTen,
            List<BaiDang> baiDangList, List<BinhLuan> binhLuanList, List<TienDo> tienDoList, 
            List<KhoaHoc> khoaHocList, List<TaiLieu> taiLieuList, VaiTro vaiTro, String anhDaiDien, String dienThoai,
            String gioiThieu, TrangThai trangThai, LocalDateTime ngayTao) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.email = email;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.baiDangList = baiDangList;
        this.binhLuanList = binhLuanList;
        this.tienDoList = tienDoList;
       
        this.khoaHocList = khoaHocList;
        this.taiLieuList = taiLieuList;
        this.vaiTro = vaiTro;
        this.anhDaiDien = anhDaiDien;
        this.dienThoai = dienThoai;
        this.gioiThieu = gioiThieu;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }
    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }
    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    public String getTenDangNhap() {
        return tenDangNhap;
    }
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public List<BaiDang> getBaiDangList() {
        return baiDangList;
    }
    public void setBaiDangList(List<BaiDang> baiDangList) {
        this.baiDangList = baiDangList;
    }
    public List<BinhLuan> getBinhLuanList() {
        return binhLuanList;
    }
    public void setBinhLuanList(List<BinhLuan> binhLuanList) {
        this.binhLuanList = binhLuanList;
    }
    public List<TienDo> getTienDoList() {
        return tienDoList;
    }
    public void setTienDoList(List<TienDo> tienDoList) {
        this.tienDoList = tienDoList;
    }
 
    public List<KhoaHoc> getKhoaHocList() {
        return khoaHocList;
    }
    public void setKhoaHocList(List<KhoaHoc> khoaHocList) {
        this.khoaHocList = khoaHocList;
    }
    public List<TaiLieu> getTaiLieuList() {
        return taiLieuList;
    }
    public void setTaiLieuList(List<TaiLieu> taiLieuList) {
        this.taiLieuList = taiLieuList;
    }
    public VaiTro getVaiTro() {
        return vaiTro;
    }
    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }
    public String getAnhDaiDien() {
        return anhDaiDien;
    }
    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }
    public String getDienThoai() {
        return dienThoai;
    }
    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
    public String getGioiThieu() {
        return gioiThieu;
    }
    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
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