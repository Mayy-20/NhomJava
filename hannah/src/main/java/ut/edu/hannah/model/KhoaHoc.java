package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "MaGiangVien", nullable = false)
    private NguoiDung giangVien;
    @OneToMany(mappedBy = "khoaHoc")
private List<BaiHoc> baiHocList;

@OneToMany(mappedBy = "khoaHoc")
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
        this.soLuongHocVien = soLuongHocVien;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
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