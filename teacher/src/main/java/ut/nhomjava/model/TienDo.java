package ut.nhomjava.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @Column(name = "PhanTramHoanThanh")
    private Float phanTramHoanThanh = 0f;

    @Column(name = "NgayCapNhat")
    private LocalDateTime ngayCapNhat = LocalDateTime.now();

    // Constructors
    public TienDo() {
        this.ngayCapNhat = LocalDateTime.now();
    }

    public TienDo(NguoiDung nguoiDung, KhoaHoc khoaHoc, Float phanTramHoanThanh) {
        this.nguoiDung = nguoiDung;
        this.khoaHoc = khoaHoc;
        this.phanTramHoanThanh = phanTramHoanThanh;
        this.ngayCapNhat = LocalDateTime.now();
    }

    // Getters and Setters
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

    public Float getPhanTramHoanThanh() {
        return phanTramHoanThanh;
    }

    public void setPhanTramHoanThanh(Float phanTramHoanThanh) {
        this.phanTramHoanThanh = phanTramHoanThanh;
    }

    public LocalDateTime getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}