package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.FormFactor;
import com.hh.computerShop.model.request.DetailRequest;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.service.ProductTypeService;

import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService {
    @Override
    public ProductRequest identifyEnum(ProductRequest productRequest) {
        List<DetailRequest> detailRequestList = productRequest.getDetails();

        detailRequestList.forEach(detailRequest -> {

        });
        return new ProductRequest();
    }
}
