package com.hh.computerShop.persist.db.h2.entity;

import com.hh.computerShop.model.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblProduct")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    private String producer;
    private Integer price;
    private Integer quantity;
    private ProductType productType;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "productEntity")
    private List<DetailEntity> details;
}
