package com.hannah.demo.dto;

import com.hannah.demo.model.NguoiDung;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDungDTO {
    private Integer maNguoiDung;
    private String tenDangNhap;
    private String email;
    private String hoTen;
    private Integer maVaiTro;
    private String tenVaiTro;
    private String anhDaiDien;
    private String dienThoai;
    private String gioiThieu;
    private NguoiDung.TrangThaiNguoiDung trangThai;
    private LocalDateTime ngayTao;
}
