package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.DesktopRequest;
import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.persist.db.h2.DesktopDetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.DesktopDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.DesktopService;
import com.hh.computerShop.support.mapper.DesktopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DesktopServiceImpl implements DesktopService {
    private final ProductRepository productRepository;
    private final DesktopDetailRepository desktopDetailRepository;

    @Override
    public DesktopResponse createDesktop(DesktopRequest desktopRequest) {
        desktopRequest.setProductType(ProductType.DESKTOP);

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
        desktopRequest.setProductType(ProductType.DESKTOP);

        Optional<ProductEntity> product = productRepository.findById(desktopRequest.getId());
        Optional<DesktopDetailEntity> desktopDetail = desktopDetailRepository.findByProductEntityId(desktopRequest.getId());

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
        DesktopDetailEntity desktopDetailEntity = desktopDetailRepository.save(desktopDetailEntityForUpdate);
        DesktopResponse desktopResponse = DesktopMapper.mapProductEntityToDesktopResponse(productEntity);

        DesktopMapper.attachDesktopDetailFromDesktopDetailEntity(desktopResponse, desktopDetailEntity);

        return desktopResponse;
    }
}
