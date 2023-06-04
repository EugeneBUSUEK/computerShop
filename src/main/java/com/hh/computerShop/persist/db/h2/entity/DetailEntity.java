package com.hh.computerShop.persist.db.h2.entity;

import com.hh.computerShop.model.enums.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblDetail")
public class DetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PropertyType propertyType;
    private String detailValue;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
