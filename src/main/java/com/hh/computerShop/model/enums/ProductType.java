package com.hh.computerShop.model.enums;

public enum ProductType {
    DESKTOP("desktop"),
    NOTEBOOK("notebook"),
    MONITOR("monitor"),
    HDD("hdd");

    private String type;

    private ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
