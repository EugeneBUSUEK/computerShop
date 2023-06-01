package com.hh.computerShop.model.enums;

public enum FormFactor {
    DESKTOP(1),
    NETTOP(2),
    MONOBLOCK(3);

    private int form;

    private FormFactor(int form) {
        this.form = form;
    }

    public Integer getForm() {
        return form;
    }
}
