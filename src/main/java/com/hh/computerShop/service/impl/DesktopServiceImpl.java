package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.ProductDto;
import com.hh.computerShop.model.request.DesktopRequest;
import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.persist.db.h2.DesktopDetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.DesktopDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.DesktopService;
import com.hh.computerShop.support.mapper.DesktopMapper;
import com.hh.computerShop.support.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DesktopServiceImpl implements DesktopService {
    private final ProductRepository productRepository;
    private final DesktopDetailRepository desktopDetailRepository;
    private final ProductMapper productMapper;

    @Override
    public DesktopResponse createDesktop(DesktopRequest desktopRequest) {
        ProductEntity productEntity = DesktopMapper.mapDesktopRequestToProductEntity(desktopRequest);
        productEntity = productRepository.save(productEntity);
        DesktopDetailEntity desktopDetailEntity = DesktopMapper.extractDesktopDetailEntityFromDesktopRequest(desktopRequest, productEntity.getId());
        desktopDetailEntity = desktopDetailRepository.save(desktopDetailEntity);
        DesktopResponse desktopResponse = DesktopMapper.mapProductEntityToDesktopResponse(productEntity);

        DesktopMapper.attachDesktopDetailFromDesktopDetailEntity(desktopResponse, desktopDetailEntity);

        return desktopResponse;
    }

    @Override
    public DesktopResponse updateDesktop(DesktopRequest desktopRequest) {
        Optional<ProductEntity> product = productRepository.findById(desktopRequest.getId());
        Optional<DesktopDetailEntity> desktopDetail = desktopDetailRepository.findById(desktopRequest.getId());

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        if (desktopDetail.isEmpty()) {
            throw new RuntimeException("desktop not found");
        }

        ProductEntity productEntityForUpdate = product.get();
        DesktopDetailEntity desktopDetailEntityForUpdate = desktopDetail.get();

        DesktopMapper.updateDesktopEntityByDesktopRequest(productEntityForUpdate, desktopRequest);
        DesktopMapper.updateDesktopDetailEntityByDesktopRequest(desktopDetailEntityForUpdate, desktopRequest);


        ProductEntity productEntity = productRepository.save(productEntityForUpdate);
        DesktopDetailEntity desktopDetailEntity = desktopDetailRepository.save(desktopDetail.get());
        DesktopResponse desktopResponse = DesktopMapper.mapProductEntityToDesktopResponse(productEntity);

        DesktopMapper.attachDesktopDetailFromDesktopDetailEntity(desktopResponse, desktopDetailEntity);

        return desktopResponse;
    }
}
