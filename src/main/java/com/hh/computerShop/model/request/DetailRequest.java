package com.hh.computerShop.model.request;

import com.hh.computerShop.model.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRequest {
    private PropertyType propertyType;
    private String propertyValue;
}
