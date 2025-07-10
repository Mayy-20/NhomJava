package ut.edu.admin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BaoCaoDTO {
    private Integer maBaoCao;
    private Integer maNguoiBaoCao;
    private String lyDo;
    private String noiDungBaoCao;
    private LocalDateTime ngayBaoCao;
}
