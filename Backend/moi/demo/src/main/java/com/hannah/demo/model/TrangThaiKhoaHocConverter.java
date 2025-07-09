package com.hannah.demo.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

// Converter này sẽ tự động được áp dụng cho thuộc tính đã được đánh dấu @Convert
@Converter
public class TrangThaiKhoaHocConverter implements AttributeConverter<KhoaHoc.TrangThai, String> {

    // Chuyển từ Java Enum -> giá trị String để lưu vào DB
    @Override
    public String convertToDatabaseColumn(KhoaHoc.TrangThai trangThai) {
        if (trangThai == null) {
            return null;
        }
        return trangThai.getDbValue();
    }

    // Chuyển từ giá trị String trong DB -> Java Enum
    @Override
    public KhoaHoc.TrangThai convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(KhoaHoc.TrangThai.values())
                .filter(c -> c.getDbValue().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + dbData));
    }
}
