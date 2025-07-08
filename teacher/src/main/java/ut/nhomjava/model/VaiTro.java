package ut.nhomjava.model;

import java.util.List;

import jakarta.persistence.CascadeType; // QUAN TRỌNG: Cần import List
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaitro")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaVaiTro")
    private Integer maVaiTro;

    @Column(name = "TenVaiTro", unique = true, nullable = false)
    private String tenVaiTro;

    // Đây là phần bạn cần kiểm tra và thêm/sửa:
    // Mối quan hệ Một Vai trò có nhiều NguoiDung
    // "mappedBy = \"vaiTro\"" chỉ ra rằng trường "vaiTro" trong entity NguoiDung là bên sở hữu mối quan hệ này.
    @OneToMany(mappedBy = "vaiTro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NguoiDung> nguoiDungList; // Tên biến này phải khớp với 'nguoiDungList' trong thông báo lỗi

    public VaiTro() {
    }

    public VaiTro(Integer maVaiTro, String tenVaiTro) {
        this.maVaiTro = maVaiTro;
        this.tenVaiTro = tenVaiTro;
    }

    // Getters and Setters
    public Integer getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(Integer maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    // Getters and Setters cho nguoiDungList
    public List<NguoiDung> getNguoiDungList() {
        return nguoiDungList;
    }

    public void setNguoiDungList(List<NguoiDung> nguoiDungList) {
        this.nguoiDungList = nguoiDungList;
    }
}