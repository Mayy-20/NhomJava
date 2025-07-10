package ut.edu.admin.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class KhoaHocDTO {
    private Integer maKhoaHoc;
    private String tenKhoaHoc;
    private String moTa;
    private Integer maGiangVien;
    private String hinhAnh;
    private Boolean mienPhi;
    private String trangThai;
    private Float danhGiaTB;
    private Integer soLuongHocVien;
    private LocalDateTime ngayTao;
    private List<Integer> maChuDe;
}
