package com.hannah.demo.dto;

import com.hannah.demo.model.BaiDang;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiDangDTO {
    private Integer maBaiDang;
    private String tieuDe;
    private String noiDung;
    private Integer maTacGia;
    private String tenTacGia;
    private Integer maChuDe;
    private String tenChuDe;
    private Integer maBaiHoc;
    private String tenBaiHoc;
    private BaiDang.TrangThaiBaiDang trangThai;
    private Integer soBaoCao;
    private Integer luotXem;
    private LocalDateTime ngayTao;
}
