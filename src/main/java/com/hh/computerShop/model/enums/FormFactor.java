package com.hh.computerShop.model.enums;

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
}
