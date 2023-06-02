package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.NotebookRequest;
import com.hh.computerShop.model.response.NotebookResponse;
import com.hh.computerShop.persist.db.h2.NotebookDetailRepository;
import com.hh.computerShop.persist.db.h2.ProductRepository;
import com.hh.computerShop.persist.db.h2.entity.NotebookDetailEntity;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import com.hh.computerShop.service.NotebookService;
import com.hh.computerShop.support.mapper.NotebookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotebookServiceImpl implements NotebookService {
    private final ProductRepository productRepository;
    private final NotebookDetailRepository notebookDetailRepository;

    @Override
    public NotebookResponse createNotebook(NotebookRequest notebookRequest) {
        notebookRequest.setProductType(ProductType.NOTEBOOK);
        ProductEntity productEntity = NotebookMapper.mapNotebookRequestToProductEntity(notebookRequest);
        productEntity = productRepository.save(productEntity);
        NotebookDetailEntity notebookDetailEntity = NotebookMapper.extractNotebookDetailEntityFromNotebookRequest(notebookRequest, productEntity.getId());
        notebookDetailEntity = notebookDetailRepository.save(notebookDetailEntity);
        NotebookResponse notebookResponse = NotebookMapper.mapProductEntityToNotebookResponse(productEntity);

        NotebookMapper.attachNotebookDetailFromNotebookDetailEntity(notebookResponse, notebookDetailEntity);

        return notebookResponse;
    }

    @Override
    public NotebookResponse updateNotebook(NotebookRequest notebookRequest) {
        notebookRequest.setProductType(ProductType.NOTEBOOK);

        Optional<ProductEntity> product = productRepository.findById(notebookRequest.getId());
        Optional<NotebookDetailEntity> notebookDetail = notebookDetailRepository.findByProductEntityId(notebookRequest.getId());

        if (product.isEmpty()) {
            throw new RuntimeException("product not found");
        }

        if (notebookDetail.isEmpty()) {
            throw new RuntimeException("notebook not found");
        }

        ProductEntity productEntityForUpdate = product.get();
        NotebookDetailEntity notebookDetailEntityForUpdate = notebookDetail.get();

        NotebookMapper.updateNotebookEntityByDesktopRequest(productEntityForUpdate, notebookRequest);
        NotebookMapper.updateNotebookDetailEntityByDesktopRequest(notebookDetailEntityForUpdate, notebookRequest);

        ProductEntity productEntity = productRepository.save(productEntityForUpdate);
        NotebookDetailEntity notebookDetailEntity = notebookDetailRepository.save(notebookDetailEntityForUpdate);
        NotebookResponse notebookResponse = NotebookMapper.mapProductEntityToNotebookResponse(productEntity);

        NotebookMapper.attachNotebookDetailFromNotebookDetailEntity(notebookResponse, notebookDetailEntity);

        return notebookResponse;
    }
}
