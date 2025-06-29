package com.hannah.demo.dto;

import com.hannah.demo.model.KhoaHoc;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhoaHocDTO {
    private Integer maKhoaHoc;
    private String tenKhoaHoc;
    private String moTa;
    private Integer maGiangVien;
    private String tenGiangVien;
    private String hinhAnh;
    private Boolean mienPhi;
    private KhoaHoc.TrangThaiKhoaHoc trangThai;
    private Float danhGiaTB;
    private Integer soLuongHocVien;
    private LocalDateTime ngayTao;
}
