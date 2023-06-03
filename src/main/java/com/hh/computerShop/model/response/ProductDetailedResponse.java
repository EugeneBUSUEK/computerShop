package com.hh.computerShop.model.response;

import com.hh.computerShop.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailedResponse {
    private Long id;
    private String serialNumber;
    private String producer;
    private Integer price;
    private Integer quantity;
    private ProductType productType;
    private Object details;
}
