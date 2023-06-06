package com.hh.computerShop.service.impl;

import com.hh.computerShop.model.enums.property.FormFactor;
import com.hh.computerShop.model.enums.property.SizeType;
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

        if (detailRequestList == null) {
            return;
        }

        detailRequestList.forEach(detailRequest -> {
            switch (detailRequest.getPropertyType()) {
                case FORM_FACTOR ->
                        detailRequest.setPropertyValue(FormFactor.getFormFactorValue(detailRequest.getPropertyValue()));
                case NOTEBOOK_SIZE ->
                        detailRequest.setPropertyValue(SizeType.getSizeTypeValue(detailRequest.getPropertyValue()));
            }
        });
    }

    @Override
    public void identifyEnum(ProductResponse productResponse) {
        List<DetailResponse> detailRequestList = productResponse.getDetails();

        detailRequestList.forEach(detailResponse -> {
            switch (detailResponse.getPropertyType()) {
                case FORM_FACTOR ->
                        detailResponse.setPropertyValue(FormFactor.getFormFactorName(detailResponse.getPropertyValue()));
                case NOTEBOOK_SIZE ->
                        detailResponse.setPropertyValue(SizeType.getSizeTypeName(detailResponse.getPropertyValue()));
            }
        });
    }
}
