package com.hh.computerShop.service;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProductsByProductType(ProductType productType);
}
