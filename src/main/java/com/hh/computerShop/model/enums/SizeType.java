package com.hh.computerShop.model.enums;

public enum SizeType {
    SIZE_TYPE_13("13"),
    SIZE_TYPE_14("14"),
    SIZE_TYPE_15("15"),
    SIZE_TYPE_17("17");

    private String sizeType;

    private SizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getSizeType() {
        return sizeType;
    }
}
