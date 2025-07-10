package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "baidang")
@Data
public class BaiDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBaiDang;

    @Column(nullable = false)
    private String tieuDe;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "maTacGia", nullable = false)
    private NguoiDung tacGia;

    @ManyToOne
    @JoinColumn(name = "maChuDe")
    private ChuDe chuDe;

    @ManyToOne
    @JoinColumn(name = "maBaiHoc")
    private BaiHoc baiHoc;

    @Enumerated(EnumType.STRING)
    private TrangThaiBaiDang trangThai = TrangThaiBaiDang.ChoDuyet;

    private Integer soBaoCao = 0;
    private Integer luotXem = 0;

    @Column(updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    public enum TrangThaiBaiDang {
        DaDuyet, ChoDuyet, An
    }
}
