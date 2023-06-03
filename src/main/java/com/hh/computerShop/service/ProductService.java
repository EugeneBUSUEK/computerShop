package com.hh.computerShop.service;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProductsByProductType(ProductType productType);
}
