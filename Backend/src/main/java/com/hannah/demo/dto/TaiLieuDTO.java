package com.hannah.demo.dto;

import com.hannah.demo.model.TaiLieu;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiLieuDTO {
    private Integer maTaiLieu;
    private String tenTaiLieu;
    private Integer maLoaiTaiLieu;
    private Integer maBaiHoc;
    private String tenBaiHoc;
    private Integer maTacGia;
    private String tenTacGia;
    private String duongDan;
    private Long kichThuoc;
    private Integer luotTai;
    private Float danhGia;
    private TaiLieu.TrangThaiTaiLieu trangThai;
    private LocalDateTime ngayTao;
    private String loaiFile;
}
