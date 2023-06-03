package com.hh.computerShop.persist.db.h2.entity;

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
@Table(name = "tblMonitorDetail")
public class MonitorDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer diagonalSize;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;
}
