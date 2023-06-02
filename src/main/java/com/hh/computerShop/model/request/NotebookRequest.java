package com.hh.computerShop.model.request;

import com.hh.computerShop.model.enums.FormFactor;
import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.enums.SizeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotebookRequest{
    private Long id;
    private String serialNumber;
    private String producer;
    private Integer price;
    private Integer quantity;
    private ProductType productType;
    private SizeType sizeType;
}
