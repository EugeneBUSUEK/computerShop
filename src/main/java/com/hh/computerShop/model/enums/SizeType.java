package com.hh.computerShop.model.enums;

public enum SizeType {
    SIZE_TYPE_13("size_type_13"),
    SIZE_TYPE_14("size_type_14"),
    SIZE_TYPE_15("size_type_15"),
    SIZE_TYPE_17("size_type_17");

    private String sizeType;

    private SizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getSizeType() {
        return sizeType;
    }
}
