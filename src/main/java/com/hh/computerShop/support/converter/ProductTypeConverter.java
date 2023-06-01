package com.hh.computerShop.support.converter;

import com.hh.computerShop.model.enums.ProductType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductType productType) {
        if (productType == null) {
            return null;
        }

        return productType.getType();
    }

    @Override
    public ProductType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(ProductType.values())
                .filter(productType -> productType.getType().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
