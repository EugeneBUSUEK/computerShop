package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.HddRequest;
import com.hh.computerShop.model.response.HddResponse;
import com.hh.computerShop.model.response.MonitorResponse;
import com.hh.computerShop.persist.db.h2.HddDetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.HddDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.MonitorDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.HddService;
import com.hh.computerShop.support.mapper.HddMapper;
import com.hh.computerShop.support.mapper.MonitorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HddServiceImpl implements HddService {
    private final ProductRepository productRepository;
    private final HddDetailRepository hddDetailRepository;

    @Override
    public HddResponse createHdd(HddRequest hddRequest) {
        hddRequest.setProductType(ProductType.HDD);

        ProductEntity productEntity = HddMapper.mapHddRequestToProductEntity(hddRequest);
        productEntity = productRepository.save(productEntity);
        HddDetailEntity hddDetailEntity = HddMapper.extractHddDetailEntityFromHddRequest(hddRequest, productEntity.getId());
        hddDetailEntity = hddDetailRepository.save(hddDetailEntity);
        HddResponse hddResponse = HddMapper.mapProductEntityToHddResponse(productEntity);

        HddMapper.attachHddDetailFromHddDetailEntity(hddResponse, hddDetailEntity);

        return hddResponse;
    }

    @Override
    public HddResponse updateHdd(HddRequest hddRequest) {
        hddRequest.setProductType(ProductType.HDD);

        Optional<ProductEntity> product = productRepository.findById(hddRequest.getId());
        Optional<HddDetailEntity> hddDetail = hddDetailRepository.findByProductEntityId(hddRequest.getId());

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        if (hddDetail.isEmpty()) {
            throw new RuntimeException("hdd not found");
        }

        ProductEntity productEntityForUpdate = product.get();
        HddDetailEntity hddDetailEntityForUpdate = hddDetail.get();

        HddMapper.updateHddEntityByHddRequest(productEntityForUpdate, hddRequest);
        HddMapper.updateHddDetailEntityByHddRequest(hddDetailEntityForUpdate, hddRequest);


        ProductEntity productEntity = productRepository.save(productEntityForUpdate);
        HddDetailEntity hddDetailEntity = hddDetailRepository.save(hddDetailEntityForUpdate);
        HddResponse hddResponse = HddMapper.mapProductEntityToHddResponse(productEntity);

        HddMapper.attachHddDetailFromHddDetailEntity(hddResponse, hddDetailEntity);

        return hddResponse;
    }
}
