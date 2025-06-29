package ut.edu.hannah.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "binhluan")
public class BinhLuan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBinhLuan;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung", nullable = false)
    private NguoiDung nguoiDung;

    @Column(name = "maKhoaHoc")
    private Integer maKhoaHoc;

    @Column(columnDefinition = "TEXT")
    private String noiDung;

    private LocalDateTime ngayTao;
    // Constructor mặc định
    public BinhLuan() {
    }

    public BinhLuan(Integer maBinhLuan, NguoiDung nguoiDung, Integer maKhoaHoc, String noiDung, LocalDateTime ngayTao) {
        this.maBinhLuan = maBinhLuan;
        this.nguoiDung = nguoiDung;
        this.maKhoaHoc = maKhoaHoc;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }

    // Getters and setters
    public Integer getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(Integer maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public Integer getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(Integer maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}