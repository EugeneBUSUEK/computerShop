package com.hh.computerShop.model.enums;

public enum ProductType {
    DESKTOP(1),
    NOTEBOOK(2),
    MONITOR(3),
    HDD(4);

    private int type;

    private ProductType(int type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
