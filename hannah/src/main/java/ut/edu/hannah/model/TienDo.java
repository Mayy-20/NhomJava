package ut.edu.hannah.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tiendo")
@Data
public class TienDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTienDo")
    private Integer maTienDo;

    @Column(name = "MaNguoiDung", nullable = false)
    private Integer maNguoiDung;

    @Column(name = "MaKhoaHoc", nullable = false)
    private Integer maKhoaHoc;

    @Column(name = "MaBaiHoc", nullable = false)
    private Integer maBaiHoc;

    @Column(name = "PhanTram")
    private Double phanTram;

    @Column(name = "ThoiGianHoc")
    private Integer thoiGianHoc;

    @Column(name = "HoanThanh")
    private Boolean hoanThanh;

    @Column(name = "LanCuoiHoc")
    private LocalDateTime lanCuoiHoc;
}