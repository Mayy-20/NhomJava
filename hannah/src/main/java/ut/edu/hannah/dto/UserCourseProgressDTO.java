package ut.edu.hannah.dto;

import java.math.BigDecimal;

public class UserCourseProgressDTO {
    private Integer maKhoaHoc;
    private String tenKhoaHoc;
    private String tenGiangVien;
    private BigDecimal phanTramHoanThanh;
    private long soBaiHocDaHoanThanh;
    private long tongSoBaiHoc;
    private String hinhAnh;

    // Constructors
    public UserCourseProgressDTO() {
    }

    public UserCourseProgressDTO(Integer maKhoaHoc, String tenKhoaHoc, String tenGiangVien,
            BigDecimal phanTramHoanThanh, long soBaiHocDaHoanThanh, long tongSoBaiHoc, String hinhAnh) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.tenGiangVien = tenGiangVien;
        this.phanTramHoanThanh = phanTramHoanThanh;
        this.soBaiHocDaHoanThanh = soBaiHocDaHoanThanh;
        this.tongSoBaiHoc = tongSoBaiHoc;
        this.hinhAnh = hinhAnh;
    }

    public Integer getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(Integer maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public BigDecimal getPhanTramHoanThanh() {
        return phanTramHoanThanh;
    }

    public void setPhanTramHoanThanh(BigDecimal phanTramHoanThanh) {
        this.phanTramHoanThanh = phanTramHoanThanh;
    }

    public long getSoBaiHocDaHoanThanh() {
        return soBaiHocDaHoanThanh;
    }

    public void setSoBaiHocDaHoanThanh(long soBaiHocDaHoanThanh) {
        this.soBaiHocDaHoanThanh = soBaiHocDaHoanThanh;
    }

    public long getTongSoBaiHoc() {
        return tongSoBaiHoc;
    }

    public void setTongSoBaiHoc(long tongSoBaiHoc) {
        this.tongSoBaiHoc = tongSoBaiHoc;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

   
    }
