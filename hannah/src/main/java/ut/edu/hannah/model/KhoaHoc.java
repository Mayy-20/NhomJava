package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// Lớp này đại diện cho một khóa học trong hệ thống
@Entity
@Table(name = "khoahoc")
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhoaHoc")
    private Integer maKhoaHoc;

    @Column(name = "TenKhoaHoc", nullable = false)
    private String tenKhoaHoc;

    @Column(name = "MoTa")
    private String moTa = "";

    // Giảng viên của khóa học
    @ManyToOne
    @JoinColumn(name = "MaGiangVien", nullable = false)
    private NguoiDung giangVien;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "MienPhi")
    private Boolean mienPhi = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai")
    private TrangThai trangThai = TrangThai.HoatDong;

    @Column(name = "DanhGiaTB")
    private Float danhGiaTB = 0.0f;

    @Column(name = "SoLuongHocVien")
    private Integer soLuongHocVien = 0;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    // Danh sách chủ đề của khóa học
    @ManyToMany
    @JoinTable(
        name = "khoahoc_chude",
        joinColumns = @JoinColumn(name = "MaKhoaHoc"),
        inverseJoinColumns = @JoinColumn(name = "MaChuDe")
    )
    private List<ChuDe> chuDeList;

    // Danh sách bài học
    @OneToMany(mappedBy = "khoaHoc")
    private List<BaiHoc> baiHocList;

    public enum TrangThai {
        HoatDong, ChoDuyet, An
    }

    // Constructor mặc định
    public KhoaHoc() {
    }

    // Constructor cơ bản
    public KhoaHoc(String tenKhoaHoc, NguoiDung giangVien) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.giangVien = giangVien;
    }

    // Getter và Setter
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
        this.moTa = moTa != null ? moTa : "";
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

    public List<ChuDe> getChuDeList() {
        return chuDeList;
    }

    public void setChuDeList(List<ChuDe> chuDeList) {
        this.chuDeList = chuDeList;
    }

    public List<BaiHoc> getBaiHocList() {
        return baiHocList;
    }

    public void setBaiHocList(List<BaiHoc> baiHocList) {
        this.baiHocList = baiHocList;
    }
}