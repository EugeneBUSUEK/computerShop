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
@Table(name = "tblProperty")
public class PropertyEntity {
    @Id
    private PropertyType propertyType;
}
