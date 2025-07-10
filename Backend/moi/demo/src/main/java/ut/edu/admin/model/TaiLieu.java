package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tailieu")
@Data
public class TaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTaiLieu;

    @Column(nullable = false)
    private String tenTaiLieu;

    @ManyToOne
    @JoinColumn(name = "maLoaiTaiLieu", nullable = false)
    private LoaiTaiLieu maLoaiTaiLieu;

    @ManyToOne
    @JoinColumn(name = "maBaiHoc", nullable = false)
    private BaiHoc maBaiHoc;

    @ManyToOne
    @JoinColumn(name = "maTacGia", nullable = false)
    private NguoiDung maTacGia;

    private String duongDan;
    private Long kichThuoc;
    private Integer luotTai = 0;
    private Float danhGia = 0.0f;

    @Enumerated(EnumType.STRING)
    private TrangThaiTaiLieu trangThai = TrangThaiTaiLieu.ChoDuyet;

    @Column(updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThaiTaiLieu {
        DaDuyet, ChoDuyet, TuChoi
    }
}