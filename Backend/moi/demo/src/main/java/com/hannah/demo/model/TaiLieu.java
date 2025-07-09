package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tailieu")
public class TaiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTaiLieu")
    private Integer maTaiLieu;

    @Column(name = "TenTaiLieu", nullable = false)
    private String tenTaiLieu;

    @ManyToOne
    @JoinColumn(name = "MaLoaiTaiLieu", nullable = false)
    private LoaiTaiLieu maLoaiTaiLieu;

    @ManyToOne
    @JoinColumn(name = "MaBaiHoc", nullable = false)
    private BaiHoc maBaiHoc;

    @ManyToOne
    @JoinColumn(name = "MaTacGia", nullable = false)
    private NguoiDung maTacGia;

    @Column(name = "DuongDan")
    private String duongDan;

    @Lob
    @Column(name = "FileContent", columnDefinition="LONGBLOB")
    private byte[] fileContent;
    
    @Column(name = "KichThuoc")
    private Long kichThuoc;

    @Column(name = "LuotTai")
    private Integer luotTai;

    @Column(name = "DanhGia")
    private Float danhGia;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable = false)
    @Convert(converter = TrangThaiTaiLieuConverter.class)
    private TrangThai trangThai;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    public enum TrangThai {
        APPROVED("DaDuyet"),
        PENDING("ChoDuyet"),
        REJECTED("TuChoi");

        private final String dbValue;

        TrangThai(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }
    }
}