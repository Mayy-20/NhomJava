package ut.nhomjava.model; // Đảm bảo đúng package

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Thêm import này
import jakarta.persistence.ManyToOne; // Thêm import này
import jakarta.persistence.Table;

@Entity
@Table(name = "baidang") // Đảm bảo tên bảng khớp chính xác
public class BaiDang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiDang") // Tên cột chính trong CSDL
    private Integer maBaiDang; // Kiểu dữ liệu Integer cho ID

    @Column(name = "TieuDe", nullable = false)
    private String tieuDe;

    @Column(name = "NoiDung")
    private String noiDung; // Ví dụ: trường nội dung bài đăng

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao; // Ngày tạo bài đăng

    // --- THÊM PHẦN NÀY ĐỂ LIÊN KẾT VỚI NguoiDung (tác giả) ---
    @ManyToOne // Một bài đăng có một tác giả (NguoiDung)
    @JoinColumn(name = "MaTacGia", nullable = false) // Tên cột khóa ngoại trong bảng 'baidang' trỏ đến 'nguoidung'
    private NguoiDung nguoiDung; // Đối tượng NguoiDung là tác giả của bài đăng
    // --- KẾT THÚC PHẦN THÊM ---


    // Constructors
    public BaiDang() {
        this.ngayTao = LocalDateTime.now(); // Khởi tạo ngày tạo mặc định
    }

    public BaiDang(String tieuDe, String noiDung, NguoiDung nguoiDung) { // Thêm NguoiDung vào constructor
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayTao = LocalDateTime.now();
        this.nguoiDung = nguoiDung; // Gán tác giả
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

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    // --- THÊM GETTER VÀ SETTER CHO NguoiDung ---
    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
    // --- KẾT THÚC PHẦN THÊM ---
}