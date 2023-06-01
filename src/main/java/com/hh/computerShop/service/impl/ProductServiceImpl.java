package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductEntity getProductById(Long id) {
        return null;
    }

    @Override
    public List<ProductEntity> getAllProductsByProductType(ProductType productType) {
        return null;
    }
}
