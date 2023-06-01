package com.hh.computerShop.support.converter;

import com.hh.computerShop.model.enums.FormFactor;
import com.hh.computerShop.model.enums.ProductType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FormFactorConverter implements AttributeConverter<FormFactor, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FormFactor formFactor) {
        if (formFactor == null) {
            return null;
        }

        return formFactor.getForm();
    }

    @Override
    public FormFactor convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(FormFactor.values())
                .filter(formFactor -> formFactor.getForm().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
