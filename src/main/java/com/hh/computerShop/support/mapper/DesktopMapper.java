package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.request.DesktopRequest;
import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.persist.db.h2.entity.DesktopDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

public class DesktopMapper {
    public static ProductEntity mapDesktopRequestToProductEntity(DesktopRequest desktopRequest) {
        return ProductEntity.builder()
                .id(desktopRequest.getId())
                .serialNumber(desktopRequest.getSerialNumber())
                .producer(desktopRequest.getProducer())
                .price(desktopRequest.getPrice())
                .quantity(desktopRequest.getQuantity())
                .productType(desktopRequest.getProductType())
                .build();
    }

    public static DesktopDetailEntity extractDesktopDetailEntityFromDesktopRequest(DesktopRequest desktopRequest, Long productId) {
        return DesktopDetailEntity.builder()
                .formFactor(desktopRequest.getFormFactor())
                .productEntity(ProductEntity.builder()
                        .id(productId)
                        .build())
                .build();
    }

    public static DesktopResponse mapProductEntityToDesktopResponse(ProductEntity productEntity) {
        return DesktopResponse.builder()
                .id(productEntity.getId())
                .serialNumber(productEntity.getSerialNumber())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .productType(productEntity.getProductType())
                .build();
    }

    public static void attachDesktopDetailFromDesktopDetailEntity(DesktopResponse desktopResponse, DesktopDetailEntity desktopDetailEntity) {
        desktopResponse.setFormFactor(desktopDetailEntity.getFormFactor());
    }

    public static void updateDesktopEntityByDesktopRequest(ProductEntity productEntityForUpdate, DesktopRequest desktopRequest) {
        productEntityForUpdate.setSerialNumber(desktopRequest.getSerialNumber() != null ? desktopRequest.getSerialNumber() : productEntityForUpdate.getSerialNumber());
        productEntityForUpdate.setProducer(desktopRequest.getProducer() != null ? desktopRequest.getProducer() : productEntityForUpdate.getProducer());
        productEntityForUpdate.setPrice(desktopRequest.getPrice() != null ? desktopRequest.getPrice() : productEntityForUpdate.getPrice());
        productEntityForUpdate.setQuantity(desktopRequest.getQuantity() != null ? desktopRequest.getQuantity() : productEntityForUpdate.getQuantity());
        productEntityForUpdate.setProductType(desktopRequest.getProductType() != null ? desktopRequest.getProductType() : productEntityForUpdate.getProductType());
    }

    public static void updateDesktopDetailEntityByDesktopRequest(DesktopDetailEntity desktopDetailEntityForUpdate, DesktopRequest desktopRequest) {
        desktopDetailEntityForUpdate.setFormFactor(desktopRequest.getFormFactor() != null ? desktopRequest.getFormFactor() : desktopDetailEntityForUpdate.getFormFactor());
    }
}