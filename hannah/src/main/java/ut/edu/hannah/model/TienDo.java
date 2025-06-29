package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tiendo")
public class TienDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTienDo;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung", nullable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "maKhoaHoc", nullable = false)
    private KhoaHoc khoaHoc;

    @ManyToOne
    @JoinColumn(name = "maBaiHoc", nullable = false)
    private BaiHoc baiHoc;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    private LocalDateTime thoiGianHoanThanh;

    public enum TrangThai {
        DANG_HOC, HOAN_THANH
    }
public TienDo() {
        // Constructor mặc định
    }
    public TienDo(Integer maTienDo, NguoiDung nguoiDung, KhoaHoc khoaHoc, BaiHoc baiHoc, TrangThai trangThai,
            LocalDateTime thoiGianHoanThanh) {
        this.maTienDo = maTienDo;
        this.nguoiDung = nguoiDung;
        this.khoaHoc = khoaHoc;
        this.baiHoc = baiHoc;
        this.trangThai = trangThai;
        this.thoiGianHoanThanh = thoiGianHoanThanh;
    }

    // Getters and setters
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

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getThoiGianHoanThanh() {
        return thoiGianHoanThanh;
    }

    public void setThoiGianHoanThanh(LocalDateTime thoiGianHoanThanh) {
        this.thoiGianHoanThanh = thoiGianHoanThanh;
    }
}