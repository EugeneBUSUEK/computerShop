package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.ProductService;
import com.hh.computerShop.support.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        ProductResponse productResponse = ProductMapper.mapProductEntityToProductResponse(product.get());

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProductsByProductType(ProductType productType) {
        List<ProductEntity> productEntities = productRepository.findAllByProductType(productType);
        List<ProductResponse> productResponseList = productEntities.stream()
                .map(ProductMapper::mapProductEntityToProductResponse).toList();

        return productResponseList;
    }
}
