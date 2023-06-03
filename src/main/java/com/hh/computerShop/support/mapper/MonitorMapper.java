package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.request.MonitorRequest;
import com.hh.computerShop.model.response.MonitorResponse;
import com.hh.computerShop.persist.db.h2.entity.MonitorDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

public class MonitorMapper {
    public static ProductEntity mapMonitorRequestToProductEntity(MonitorRequest monitorRequest) {
        return ProductEntity.builder()
                .id(monitorRequest.getId())
                .serialNumber(monitorRequest.getSerialNumber())
                .producer(monitorRequest.getProducer())
                .price(monitorRequest.getPrice())
                .quantity(monitorRequest.getQuantity())
                .productType(monitorRequest.getProductType())
                .build();
    }

    public static MonitorDetailEntity extractMonitorDetailEntityFromMonitorRequest(MonitorRequest monitorRequest, Long id) {
        return MonitorDetailEntity.builder()
                .diagonalSize(monitorRequest.getDiagonalSize())
                .productEntity(ProductEntity.builder()
                        .id(id)
                        .build())
                .build();
    }

    public static MonitorResponse mapProductEntityToMonitorResponse(ProductEntity productEntity) {
        return MonitorResponse.builder()
                .id(productEntity.getId())
                .serialNumber(productEntity.getSerialNumber())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .productType(productEntity.getProductType())
                .build();
    }

    public static void attachMonitorDetailFromMonitorDetailEntity(MonitorResponse monitorResponse, MonitorDetailEntity monitorDetailEntity) {
        monitorResponse.setDiagonalSize(monitorDetailEntity.getDiagonalSize());
    }

    public static void updateDesktopEntityByDesktopRequest(ProductEntity productEntityForUpdate, MonitorRequest monitorRequest) {
        productEntityForUpdate.setSerialNumber(monitorRequest.getSerialNumber() != null ? monitorRequest.getSerialNumber() : productEntityForUpdate.getSerialNumber());
        productEntityForUpdate.setProducer(monitorRequest.getProducer() != null ? monitorRequest.getProducer() : productEntityForUpdate.getProducer());
        productEntityForUpdate.setPrice(monitorRequest.getPrice() != null ? monitorRequest.getPrice() : productEntityForUpdate.getPrice());
        productEntityForUpdate.setQuantity(monitorRequest.getQuantity() != null ? monitorRequest.getQuantity() : productEntityForUpdate.getQuantity());
        productEntityForUpdate.setProductType(monitorRequest.getProductType() != null ? monitorRequest.getProductType() : productEntityForUpdate.getProductType());
    }

    public static void updateDesktopDetailEntityByDesktopRequest(MonitorDetailEntity monitorDetailEntityForUpdate, MonitorRequest monitorRequest) {
        monitorDetailEntityForUpdate.setDiagonalSize(monitorRequest.getDiagonalSize() != null ? monitorRequest.getDiagonalSize() : monitorDetailEntityForUpdate.getDiagonalSize());
    }
}
