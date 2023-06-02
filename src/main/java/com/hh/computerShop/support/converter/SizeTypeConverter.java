package com.hh.computerShop.support.converter;

import com.hh.computerShop.model.enums.SizeType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SizeTypeConverter implements AttributeConverter<SizeType, String> {
    @Override
    public String convertToDatabaseColumn(SizeType attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getSizeType();
    }

    @Override
    public SizeType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(SizeType.values())
                .filter(sizeType -> sizeType.getSizeType().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
