package com.hh.computerShop.support.converter;

import com.hh.computerShop.model.enums.PropertyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PropertyTypeConverter implements AttributeConverter<PropertyType, String> {
    @Override
    public String convertToDatabaseColumn(PropertyType propertyType) {
        if (propertyType == null) {
            return null;
        }

        return propertyType.getPropertyType();
    }

    @Override
    public PropertyType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(PropertyType.values())
                .filter(propertyType -> propertyType.getPropertyType().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
