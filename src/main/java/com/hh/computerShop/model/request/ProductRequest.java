package com.hh.computerShop.model.request;

import com.hh.computerShop.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Long id;
    private String serialNumber;
    private String producer;
    private Integer price;
    private Integer quantity;
    private ProductType productType;
    private List<DetailRequest> details;
}
