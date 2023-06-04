package com.hh.computerShop.model.enums.property;

import java.util.stream.Stream;

public enum FormFactor {
    DESKTOP("desktop"),
    NETTOP("nettop"),
    MONOBLOCK("monoblock");

    private String form;

    private FormFactor(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }

    public static String getFormFactorValue(String formFactorName) {
        String finalFormFactorName = formFactorName.toUpperCase();

        return Stream.of(FormFactor.values())
                .filter(f -> f.name().equals(finalFormFactorName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .getForm();
    }

    public static String getFormFactorName(String form) {
        return Stream.of(FormFactor.values())
                .filter(f -> f.getForm().equals(form))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .name();
    }
}
