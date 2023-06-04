package com.hh.computerShop.support.mapper;

import com.hh.computerShop.model.request.DetailRequest;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.response.DetailResponse;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.persist.db.h2.entity.DetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static void mapProductRequestToProductEntity(ProductRequest productRequest, ProductEntity productEntity, List<DetailEntity> detailEntityList) {
        productEntity.setId(productRequest.getId() != null ? productRequest.getId() : productEntity.getId());
        productEntity.setSerialNumber(productRequest.getSerialNumber() != null ? productRequest.getSerialNumber() : productEntity.getSerialNumber());
        productEntity.setProducer(productRequest.getProducer() != null ? productRequest.getProducer() : productEntity.getProducer());
        productEntity.setPrice(productRequest.getPrice() != null ? productRequest.getPrice() : productEntity.getPrice());
        productEntity.setQuantity(productRequest.getQuantity() != null ? productRequest.getQuantity() : productEntity.getQuantity());
        productEntity.setProductType(productRequest.getProductType() != null ? productRequest.getProductType() : productEntity.getProductType());
        productEntity.setDetails(detailEntityList);
    }

    public static ProductResponse mapProductEntityToProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .serialNumber(productEntity.getSerialNumber())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .productType(productEntity.getProductType())
                .details(mapDetailEntityListToDetailResponseList(productEntity.getDetails()))
                .build();
    }

    public static List<DetailEntity> mapDetailRequestListToDetailEntityList(List<DetailRequest> detailRequestList, Long productId) {
        List<DetailEntity> detailEntityList = detailRequestList.stream().map(detailRequest -> DetailEntity.builder()
                .propertyType(detailRequest.getPropertyType())
                .detailValue(detailRequest.getPropertyValue())
                .productEntity(ProductEntity.builder().id(productId).build())
                .build()).toList();

        return detailEntityList;
    }

    public static void updateDetailEntityListByDetailRequestList(List<DetailRequest> detailRequestList, List<DetailEntity> detailEntityList, Long productId) {
        LOOP1:
        for (int requestListIndex = 0; requestListIndex < detailRequestList.size(); requestListIndex++) {
            boolean isChanged = false;
            for (int entityListIndex = 0; entityListIndex < detailEntityList.size(); entityListIndex++) {
                if (detailRequestList.get(requestListIndex).getPropertyType().equals(detailEntityList.get(entityListIndex).getPropertyType())) {
                    detailEntityList.get(entityListIndex).setDetailValue(detailRequestList.get(requestListIndex).getPropertyValue());
                    isChanged = true;
                }

                if (isChanged) {
                    continue LOOP1;
                }

                detailEntityList.add(DetailEntity.builder()
                        .propertyType(detailRequestList.get(requestListIndex).getPropertyType())
                        .detailValue(detailRequestList.get(requestListIndex).getPropertyValue())
                        .productEntity(ProductEntity.builder().id(productId).build())
                        .build());
            }
        }
    }

    public static List<DetailResponse> mapDetailEntityListToDetailResponseList(List<DetailEntity> detailEntityList) {
        List<DetailResponse> detailResponseList = new ArrayList<>();

        detailEntityList.forEach(detailEntity -> {
            detailResponseList.add(DetailResponse.builder()
                    .propertyType(detailEntity.getPropertyType())
                    .propertyValue(detailEntity.getDetailValue())
                    .build());
        });

        return detailResponseList;
    }
}
