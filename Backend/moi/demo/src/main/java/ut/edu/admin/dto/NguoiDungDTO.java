package ut.edu.admin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NguoiDungDTO {
    private Integer maNguoiDung;
    private String tenDangNhap;
    private String email;
    private String matKhau;
    private String hoTen;
    private Integer maVaiTro;
    private String anhDaiDien;
    private String dienThoai;
    private String gioiThieu;
    private String trangThai;
    private LocalDateTime ngayTao;
}
