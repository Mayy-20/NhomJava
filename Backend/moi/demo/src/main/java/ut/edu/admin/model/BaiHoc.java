package ut.edu.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "baihoc")
@Data
public class BaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBaiHoc;

    @Column(nullable = false)
    private String tenBaiHoc;

    @ManyToOne
    @JoinColumn(name = "maKhoaHoc", nullable = false)
    private KhoaHoc maKhoaHoc;

    @Column(nullable = false)
    private Integer thuTu;

    private String videoURL;

    @Enumerated(EnumType.STRING)
    private CapDo capDo = CapDo.CoBan;

    private String thoiLuong;

    public enum CapDo {
        CoBan, TrungCap, NangCao
    }
}
