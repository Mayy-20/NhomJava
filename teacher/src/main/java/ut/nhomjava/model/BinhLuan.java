package ut.nhomjava.model; // Đã đổi package

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "binhluan")
public class BinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBinhLuan")
    private Integer maBinhLuan;

    @Column(name = "NoiDung", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "MaBaiDang", nullable = false)
    private BaiDang baiDang; // Cần có class BaiDang.java

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = false)
    private NguoiDung nguoiDung; // Cần có class NguoiDung.java

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao; // Đặt khởi tạo trong constructor

    // Constructor mặc định (cần thiết cho JPA)
    public BinhLuan() {
        this.ngayTao = LocalDateTime.now(); // Khởi tạo ngayTao khi tạo đối tượng
    }

    public BinhLuan(String noiDung, BaiDang baiDang, NguoiDung nguoiDung) {
        this.noiDung = noiDung;
        this.baiDang = baiDang;
        this.nguoiDung = nguoiDung;
        this.ngayTao = LocalDateTime.now(); // Khởi tạo ngayTao
    }

    // Getters and Setters
    public Integer getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(Integer maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public BaiDang getBaiDang() {
        return baiDang;
    }

    public void setBaiDang(BaiDang baiDang) {
        this.baiDang = baiDang;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}