package ut.edu.admin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaiLieuDTO {
    private Integer maTaiLieu;
    private String tenTaiLieu;
    private Integer maLoaiTaiLieu;
    private Integer maBaiHoc;
    private Integer maTacGia;
    private String duongDan;
    private Long kichThuoc;
    private Integer luotTai;
    private Float danhGia;
    private String trangThai;
    private LocalDateTime ngayTao;
}