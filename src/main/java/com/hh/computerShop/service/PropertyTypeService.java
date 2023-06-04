package com.hh.computerShop.service;

import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.response.ProductResponse;

public interface PropertyTypeService {
    void identifyEnum(ProductRequest productRequest);

    void identifyEnum(ProductResponse productResponse);
}
