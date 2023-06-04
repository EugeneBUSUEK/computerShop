package com.hh.computerShop.model.response;

import com.hh.computerShop.model.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponse {
    private PropertyType propertyType;
    private String propertyValue;
}
