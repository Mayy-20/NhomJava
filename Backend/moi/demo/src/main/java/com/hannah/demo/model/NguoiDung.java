package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nguoidung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "TenDangNhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    @ManyToOne
    @JoinColumn(name = "MaVaiTro", nullable = false)
    private VaiTro maVaiTro;

    @Column(name = "AnhDaiDien")
    private String anhDaiDien;

    @Lob
    @Column(name = "AvatarContent", columnDefinition="LONGBLOB")
    private byte[] avatarContent;

    @Column(name = "DienThoai")
    private String dienThoai;

    @Column(name = "GioiThieu")
    private String gioiThieu;

    @Enumerated(EnumType.STRING)
    @Column(name = "TrangThai", nullable = false)
    @Convert(converter = TrangThaiNguoiDungConverter.class)
    private TrangThai trangThai;

    @Column(name = "NgayTao", nullable = false)
    private LocalDateTime ngayTao;

    public enum TrangThai {
        ACTIVE("Active"),
        INACTIVE("Inactive"),
        BANNED("Banned"),
        PENDING("Active"); // Workaround: PENDING users are stored as 'Active' in DB

        private final String dbValue;

        TrangThai(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }
    }
}