package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.MonitorRequest;
import com.hh.computerShop.model.response.MonitorResponse;
import com.hh.computerShop.persist.db.h2.MonitorDetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.MonitorDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.MonitorService;
import com.hh.computerShop.support.mapper.MonitorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonitorServiceImpl implements MonitorService {
    private final ProductRepository productRepository;
    private final MonitorDetailRepository monitorDetailRepository;

    @Override
    public MonitorResponse createMonitor(MonitorRequest monitorRequest) {
        monitorRequest.setProductType(ProductType.MONITOR);

        ProductEntity productEntity = MonitorMapper.mapMonitorRequestToProductEntity(monitorRequest);
        productEntity = productRepository.save(productEntity);
        MonitorDetailEntity monitorDetailEntity = MonitorMapper.extractMonitorDetailEntityFromMonitorRequest(monitorRequest, productEntity.getId());
        monitorDetailEntity = monitorDetailRepository.save(monitorDetailEntity);
        MonitorResponse monitorResponse = MonitorMapper.mapProductEntityToMonitorResponse(productEntity);

        MonitorMapper.attachMonitorDetailFromMonitorDetailEntity(monitorResponse, monitorDetailEntity);

        return monitorResponse;
    }

    @Override
    public MonitorResponse updateMonitor(MonitorRequest monitorRequest) {
        monitorRequest.setProductType(ProductType.MONITOR);

        Optional<ProductEntity> product = productRepository.findById(monitorRequest.getId());
        Optional<MonitorDetailEntity> monitorDetail = monitorDetailRepository.findByProductEntityId(monitorRequest.getId());

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        if (monitorDetail.isEmpty()) {
            throw new RuntimeException("monitor not found");
        }

        ProductEntity productEntityForUpdate = product.get();
        MonitorDetailEntity monitorDetailEntityForUpdate = monitorDetail.get();

        MonitorMapper.updateMonitorEntityByMonitorRequest(productEntityForUpdate, monitorRequest);
        MonitorMapper.updateMonitorDetailEntityByMonitorRequest(monitorDetailEntityForUpdate, monitorRequest);


        ProductEntity productEntity = productRepository.save(productEntityForUpdate);
        MonitorDetailEntity monitorDetailEntity = monitorDetailRepository.save(monitorDetailEntityForUpdate);
        MonitorResponse monitorResponse = MonitorMapper.mapProductEntityToMonitorResponse(productEntity);

        MonitorMapper.attachMonitorDetailFromMonitorDetailEntity(monitorResponse, monitorDetailEntity);

        return monitorResponse;
    }
}
