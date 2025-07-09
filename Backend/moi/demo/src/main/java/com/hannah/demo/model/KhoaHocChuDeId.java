package com.hannah.demo.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KhoaHocChuDeId implements Serializable {

    private Long maKhoaHoc;
    private Long maChuDe;

    public KhoaHocChuDeId() {}

    public KhoaHocChuDeId(Long maKhoaHoc, Long maChuDe) {
        this.maKhoaHoc = maKhoaHoc;
        this.maChuDe = maChuDe;
    }

    // getters và setters
    public Long getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(Long maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public Long getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(Long maChuDe) {
        this.maChuDe = maChuDe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhoaHocChuDeId that = (KhoaHocChuDeId) o;
        return Objects.equals(maKhoaHoc, that.maKhoaHoc) &&
               Objects.equals(maChuDe, that.maChuDe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKhoaHoc, maChuDe);
    }
}
