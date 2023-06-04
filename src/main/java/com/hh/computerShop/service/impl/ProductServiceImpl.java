package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.persist.db.h2.DetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.DetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.ProductService;
import com.hh.computerShop.support.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final DetailRepository detailRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();

        ProductMapper.mapProductRequestToProductEntity(productRequest, productEntity, productEntity.getDetails());

        productEntity = productRepository.save(productEntity);

        List<DetailEntity> detailEntityList = ProductMapper.mapDetailRequestListToDetailEntityList(productRequest.getDetails(), productEntity.getId());

        detailEntityList = (List<DetailEntity>) detailRepository.saveAll(detailEntityList);
        productEntity.setDetails(detailEntityList);

        ProductResponse productResponse = ProductMapper.mapProductEntityToProductResponse(productEntity);

        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        Optional<ProductEntity> product = productRepository.findById(productRequest.getId());

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        ProductEntity productEntityForUpdate = product.get();

        ProductMapper.mapProductRequestToProductEntity(productRequest, productEntityForUpdate, productEntityForUpdate.getDetails());
        productEntityForUpdate = productRepository.save(productEntityForUpdate);

        List<DetailEntity> detailEntityListForUpdate = detailRepository.findAllByProductEntityId(productEntityForUpdate.getId());

        ProductMapper.updateDetailEntityListByDetailRequestList(productRequest.getDetails(), detailEntityListForUpdate, productEntityForUpdate.getId());

        detailEntityListForUpdate = (List<DetailEntity>) detailRepository.saveAll(detailEntityListForUpdate);
        ProductResponse productResponse = ProductMapper.mapProductEntityToProductResponse(productEntityForUpdate);

        return productResponse;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        ProductEntity productEntity = product.get();
        List<DetailEntity> detailEntityList = detailRepository.findAllByProductEntityId(productEntity.getId());

        ProductResponse productResponse = ProductMapper.mapProductEntityToProductResponse(productEntity);

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
