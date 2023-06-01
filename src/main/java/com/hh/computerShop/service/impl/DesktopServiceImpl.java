package com.hh.computerShop.service.impl;

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

@Service
@RequiredArgsConstructor
public class DesktopServiceImpl implements DesktopService {
    private final ProductRepository productRepository;
    private final DesktopDetailRepository desktopDetailRepository;

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
        return null;
    }
}
