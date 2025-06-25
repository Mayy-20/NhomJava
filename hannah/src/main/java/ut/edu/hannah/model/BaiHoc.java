package ut.edu.hannah.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "baihoc")
@Data
public class BaiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBaiHoc")
    private Integer maBaiHoc;

    @Column(name = "TenBaiHoc", nullable = false)
    private String tenBaiHoc;

    @Column(name = "MaKhoaHoc", nullable = false)
    private Integer maKhoaHoc;

    @Column(name = "ThuTu", nullable = false)
    private Integer thuTu;

    @Column(name = "VideoURL")
    private String videoURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "CapDo")
    private CapDo capDo;

    @Column(name = "ThoiLuong")
    private String thoiLuong;

    public enum CapDo {
        CoBan, TrungCap, NangCao
    }
}