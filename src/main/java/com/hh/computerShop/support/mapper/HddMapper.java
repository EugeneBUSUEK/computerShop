package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.request.HddRequest;
import com.hh.computerShop.model.response.HddResponse;
import com.hh.computerShop.model.response.MonitorResponse;
import com.hh.computerShop.persist.db.h2.entity.HddDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.MonitorDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

public class HddMapper {
    public static ProductEntity mapHddRequestToProductEntity(HddRequest hddRequest) {
        return ProductEntity.builder()
                .id(hddRequest.getId())
                .serialNumber(hddRequest.getSerialNumber())
                .producer(hddRequest.getProducer())
                .price(hddRequest.getPrice())
                .quantity(hddRequest.getQuantity())
                .productType(hddRequest.getProductType())
                .build();
    }

    public static HddDetailEntity extractHddDetailEntityFromHddRequest(HddRequest hddRequest, Long id) {
        return HddDetailEntity.builder()
                .capacity(hddRequest.getCapacity())
                .productEntity(ProductEntity.builder()
                        .id(id)
                        .build())
                .build();
    }

    public static HddResponse mapProductEntityToHddResponse(ProductEntity productEntity) {
        return HddResponse.builder()
                .id(productEntity.getId())
                .serialNumber(productEntity.getSerialNumber())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .productType(productEntity.getProductType())
                .build();
    }

    public static void attachHddDetailFromHddDetailEntity(HddResponse hddResponse, HddDetailEntity hddDetailEntity) {
        hddResponse.setCapacity(hddDetailEntity.getCapacity());
    }

    public static void updateHddEntityByHddRequest(ProductEntity productEntityForUpdate, HddRequest hddRequest) {
        productEntityForUpdate.setSerialNumber(hddRequest.getSerialNumber() != null ? hddRequest.getSerialNumber() : productEntityForUpdate.getSerialNumber());
        productEntityForUpdate.setProducer(hddRequest.getProducer() != null ? hddRequest.getProducer() : productEntityForUpdate.getProducer());
        productEntityForUpdate.setPrice(hddRequest.getPrice() != null ? hddRequest.getPrice() : productEntityForUpdate.getPrice());
        productEntityForUpdate.setQuantity(hddRequest.getQuantity() != null ? hddRequest.getQuantity() : productEntityForUpdate.getQuantity());
        productEntityForUpdate.setProductType(hddRequest.getProductType() != null ? hddRequest.getProductType() : productEntityForUpdate.getProductType());
    }

    public static void updateHddDetailEntityByHddRequest(HddDetailEntity hddDetailEntityForUpdate, HddRequest hddRequest) {
        hddDetailEntityForUpdate.setCapacity(hddRequest.getCapacity() != null ? hddRequest.getCapacity() : hddDetailEntityForUpdate.getCapacity());
    }
}
