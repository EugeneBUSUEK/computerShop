package com.hh.computerShop.support.converter;

import com.hh.computerShop.model.enums.property.FormFactor;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class FormFactorConverter implements AttributeConverter<FormFactor, String> {
    @Override
    public String convertToDatabaseColumn(FormFactor formFactor) {
        if (formFactor == null) {
            return null;
        }

        return formFactor.getForm();
    }

    @Override
    public FormFactor convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(FormFactor.values())
                .filter(formFactor -> formFactor.getForm().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
