package com.hannah.demo.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true) // autoApply = true để JPA tự động dùng converter này cho kiểu TrangThai
public class TrangThaiTaiLieuConverter implements AttributeConverter<TaiLieu.TrangThai, String> {

    @Override
    public String convertToDatabaseColumn(TaiLieu.TrangThai trangThai) {
        if (trangThai == null) {
            return null;
        }
        return trangThai.getDbValue();
    }

    @Override
    public TaiLieu.TrangThai convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(TaiLieu.TrangThai.values())
                .filter(c -> c.getDbValue().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Giá trị không hợp lệ cho TrangThai TaiLieu: " + dbData));
    }
}