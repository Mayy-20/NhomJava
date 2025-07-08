package ut.nhomjava.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "khoahoc")
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoaHoc")
    private Integer maKhoaHoc;

    @Column(name = "TenKhoaHoc", nullable = false, unique = true)
    private String tenKhoaHoc;

    @Column(name = "MoTa")
    private String moTa;

    @ManyToOne(fetch = FetchType.EAGER) // THAY ĐỔI TẠI ĐÂY: Thêm fetch = FetchType.EAGER
    @JoinColumn(name = "MaGiangVien", nullable = false)
    private NguoiDung giangVien;

    @OneToMany(mappedBy = "khoaHoc", cascade = CascadeType.ALL, orphanRemoval = true) // Thêm cascade và orphanRemoval
    private List<BaiHoc> baiHocList;

    @OneToMany(mappedBy = "khoaHoc", cascade = CascadeType.ALL, orphanRemoval = true) // Thêm cascade và orphanRemoval
    private List<TienDo> tienDoList;

    // @OneToMany(mappedBy = "khoaHoc")
    // private List<DangKy> dangKyList;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "MienPhi")
    private Boolean mienPhi = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.HoatDong;

    @Column(name = "DanhGiaTB")
    private Float danhGiaTB = 0f;

    @Column(name = "SoLuongHocVien")
    private Integer soLuongHocVien = 0;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();
 @Column(name = "NgayCapNhat") // Đảm bảo tên cột trong cơ sở dữ liệu khớp
    private LocalDateTime ngayCapNhat;
    @ManyToMany
    @JoinTable(
        name = "khoahoc_chude",
        joinColumns = @JoinColumn(name = "MaKhoaHoc"),
        inverseJoinColumns = @JoinColumn(name = "MaChuDe")
    )
    private Set<ChuDe> chuDes;

    @Enumerated(EnumType.STRING)
    @Column(name = "CapDo", nullable = false)
    private CapDo capDo = CapDo.NguoiMoi;

    public enum CapDo {
        NguoiMoi,
        TrungCap,
        NangCao
    }

    public enum TrangThai {
        HoatDong,
        ChoDuyet,
        An
    }

    // Constructor
    public KhoaHoc() {}

    public KhoaHoc(Integer maKhoaHoc, String tenKhoaHoc, String moTa, NguoiDung giangVien, List<BaiHoc> baiHocList,
                   List<TienDo> tienDoList, String hinhAnh, Boolean mienPhi, TrangThai trangThai,
                   Float danhGiaTB, Integer soLuongHocVien, LocalDateTime ngayTao, Set<ChuDe> chuDes, CapDo capDo) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.moTa = moTa;
        this.giangVien = giangVien;
        this.baiHocList = baiHocList;
        this.tienDoList = tienDoList;
        this.hinhAnh = hinhAnh;
        this.mienPhi = mienPhi;
        this.trangThai = trangThai;
        this.danhGiaTB = danhGiaTB;
        this.soLuongHocVien = soLuongHocVien;
        this.ngayTao = ngayTao;
        this.chuDes = chuDes;
        this.capDo = capDo;
    }

    public KhoaHoc(Integer maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    // Getters and Setters
    public Integer getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(Integer maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public NguoiDung getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(NguoiDung giangVien) {
        this.giangVien = giangVien;
    }

    public List<BaiHoc> getBaiHocList() {
        return baiHocList;
    }

    public void setBaiHocList(List<BaiHoc> baiHocList) {
        this.baiHocList = baiHocList;
    }

    public List<TienDo> getTienDoList() {
        return tienDoList;
    }

    public void setTienDoList(List<TienDo> tienDoList) {
        this.tienDoList = tienDoList;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Boolean getMienPhi() {
        return mienPhi;
    }

    public void setMienPhi(Boolean mienPhi) {
        this.mienPhi = mienPhi;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public Float getDanhGiaTB() {
        return danhGiaTB;
    }

    public void setDanhGiaTB(Float danhGiaTB) {
        this.danhGiaTB = danhGiaTB;
    }

    public Integer getSoLuongHocVien() {
        return soLuongHocVien;
    }

    public void setSoLuongHocVien(Integer soLuongHocVien) {
        this.soLuongHocVien = soLuongHocVien; // Đã sửa lỗi đánh máy tại đây
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
// THÊM GETTER VÀ SETTER NÀY CHO NGAYCAPNHAT
    public LocalDateTime getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    public Set<ChuDe> getChuDes() {
        return chuDes;
    }

    public void setChuDes(Set<ChuDe> chuDes) {
        this.chuDes = chuDes;
    }

    public CapDo getCapDo() {
        return capDo;
    }

    public void setCapDo(CapDo capDo) {
        this.capDo = capDo;
    }
}