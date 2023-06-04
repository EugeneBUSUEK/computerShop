package com.hh.computerShop.model.enums;

public enum PropertyType {
    FORM_FACTOR("form_factor"),
    NOTEBOOK_SIZE("notebook_size"),
    DIAGONAL_SIZE("diagonal_size"),
    CAPACITY("capacity");

    private String propertyType;

    private PropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyType() {
        return propertyType;
    }
}
