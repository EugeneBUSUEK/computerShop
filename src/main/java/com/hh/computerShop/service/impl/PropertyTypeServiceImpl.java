package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.FormFactor;
import com.hh.computerShop.model.enums.SizeType;
import com.hh.computerShop.model.request.DetailRequest;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.response.DetailResponse;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.service.PropertyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyTypeServiceImpl implements PropertyTypeService {
    @Override
    public void identifyEnum(ProductRequest productRequest) {
        List<DetailRequest> detailRequestList = productRequest.getDetails();

        detailRequestList.forEach(detailRequest -> {
            switch (detailRequest.getPropertyType()) {
                case FORM_FACTOR -> detailRequest.setPropertyValue(FormFactor.valueOf(detailRequest.getPropertyValue().toUpperCase()).getForm());
                case NOTEBOOK_SIZE -> detailRequest.setPropertyValue(SizeType.valueOf(detailRequest.getPropertyValue().toUpperCase()).getSizeType());
            }
        });
    }

    @Override
    public void identifyEnum(ProductResponse productResponse) {
        List<DetailResponse> detailRequestList = productResponse.getDetails();

        detailRequestList.forEach(detailRequest -> {
            switch (detailRequest.getPropertyType()) {
                case FORM_FACTOR -> detailRequest.setPropertyValue(FormFactor.valueOf(detailRequest.getPropertyValue().toUpperCase()).getForm().toUpperCase());
                case NOTEBOOK_SIZE -> detailRequest.setPropertyValue(SizeType.valueOf(detailRequest.getPropertyValue().toUpperCase()).getSizeType().toUpperCase());
            }
        });
    }
}
