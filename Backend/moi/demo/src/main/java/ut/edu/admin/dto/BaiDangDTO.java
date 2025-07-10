package ut.edu.admin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BaiDangDTO {
    private Integer maBaiDang;
    private String tieuDe;
    private String noiDung;
    private Integer maTacGia;
    private Integer maChuDe;
    private Integer maBaiHoc;
    private String trangThai;
    private Integer soBaoCao;
    private Integer luotXem;
    private LocalDateTime ngayTao;
}
