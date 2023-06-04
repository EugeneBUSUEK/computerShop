package com.hh.computerShop.model.enums.property;

import java.util.stream.Stream;

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

    public static String getSizeTypeValue(String sizeTypeName) {
        String finalSizeTypeName = sizeTypeName.toUpperCase();

        return Stream.of(SizeType.values())
                .filter(s -> s.name().equals(finalSizeTypeName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .getSizeType();
    }

    public static String getSizeTypeName(String sizeType) {
        return Stream.of(SizeType.values())
                .filter(s -> s.getSizeType().equals(sizeType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .name();
    }
}
