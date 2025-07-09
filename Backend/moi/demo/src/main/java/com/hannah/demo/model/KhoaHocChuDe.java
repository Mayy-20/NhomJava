package com.hannah.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "khoahoc_chude")
public class KhoaHocChuDe {

    @EmbeddedId
    private KhoaHocChuDeId id;

    @ManyToOne
    @MapsId("maKhoaHoc")
    @JoinColumn(name = "MaKhoaHoc", nullable = false)
    private KhoaHoc maKhoaHoc;

    @ManyToOne
    @MapsId("maChuDe")
    @JoinColumn(name = "MaChuDe", nullable = false)
    private ChuDe maChuDe;
}
