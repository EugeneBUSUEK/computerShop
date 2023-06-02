package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.request.NotebookRequest;
import com.hh.computerShop.model.response.NotebookResponse;
import com.hh.computerShop.persist.db.h2.entity.NotebookDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

public class NotebookMapper {

    public static ProductEntity mapNotebookRequestToProductEntity(NotebookRequest notebookRequest) {
        return ProductEntity.builder()
                .id(notebookRequest.getId())
                .serialNumber(notebookRequest.getSerialNumber())
                .producer(notebookRequest.getProducer())
                .price(notebookRequest.getPrice())
                .quantity(notebookRequest.getQuantity())
                .productType(notebookRequest.getProductType())
                .build();
    }

    public static NotebookDetailEntity extractNotebookDetailEntityFromNotebookRequest(NotebookRequest notebookRequest, Long id) {
        return NotebookDetailEntity.builder()
                .sizeType(notebookRequest.getSizeType())
                .productEntity(ProductEntity.builder()
                        .id(id)
                        .build())
                .build();
    }

    public static NotebookResponse mapProductEntityToNotebookResponse(ProductEntity productEntity) {
        return NotebookResponse.builder()
                .id(productEntity.getId())
                .serialNumber(productEntity.getSerialNumber())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .productType(productEntity.getProductType())
                .build();
    }

    public static void attachNotebookDetailFromNotebookDetailEntity(NotebookResponse notebookResponse, NotebookDetailEntity notebookDetailEntity) {
        notebookResponse.setSizeType(notebookDetailEntity.getSizeType());
    }

    public static void updateNotebookEntityByDesktopRequest(ProductEntity productEntityForUpdate, NotebookRequest notebookRequest) {
        productEntityForUpdate.setSerialNumber(notebookRequest.getSerialNumber() != null ? notebookRequest.getSerialNumber() : productEntityForUpdate.getSerialNumber());
        productEntityForUpdate.setProducer(notebookRequest.getProducer() != null ? notebookRequest.getProducer() : productEntityForUpdate.getProducer());
        productEntityForUpdate.setPrice(notebookRequest.getPrice() != null ? notebookRequest.getPrice() : productEntityForUpdate.getPrice());
        productEntityForUpdate.setQuantity(notebookRequest.getQuantity() != null ? notebookRequest.getQuantity() : productEntityForUpdate.getQuantity());
        productEntityForUpdate.setProductType(notebookRequest.getProductType() != null ? notebookRequest.getProductType() : productEntityForUpdate.getProductType());
    }

    public static void updateNotebookDetailEntityByDesktopRequest(NotebookDetailEntity notebookDetailEntityForUpdate, NotebookRequest notebookRequest) {
        notebookDetailEntityForUpdate.setSizeType(notebookRequest.getSizeType() != null ? notebookRequest.getSizeType() : notebookDetailEntityForUpdate.getSizeType());
    }
}
