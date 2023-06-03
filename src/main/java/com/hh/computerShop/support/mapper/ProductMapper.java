package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

public class ProductMapper {
    public static ProductResponse mapProductEntityToProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .serialNumber(productEntity.getSerialNumber())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .productType(productEntity.getProductType())
                .build();
    }
}
