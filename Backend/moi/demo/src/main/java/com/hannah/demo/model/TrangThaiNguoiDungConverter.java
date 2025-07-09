package com.hannah.demo.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class TrangThaiNguoiDungConverter implements AttributeConverter<NguoiDung.TrangThai, String> {

    @Override
    public String convertToDatabaseColumn(NguoiDung.TrangThai trangThai) {
        if (trangThai == null) {
            return null;
        }
        return trangThai.getDbValue();
    }

    @Override
    public NguoiDung.TrangThai convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        // Vì cả ACTIVE và PENDING đều là 'Active' trong DB,
        // chúng ta mặc định coi 'Active' là ACTIVE.
        // Logic phân biệt PENDING sẽ phải nằm ở tầng Service nếu cần.
        return Stream.of(NguoiDung.TrangThai.values())
                .filter(c -> c.getDbValue().equals(dbData) && c != NguoiDung.TrangThai.PENDING)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + dbData));
    }
}