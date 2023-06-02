package com.hh.computerShop.persist.db.h2.entity;

import com.hh.computerShop.model.enums.SizeType;
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
@Table(name = "tblNotebookDetail")
public class NotebookDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private SizeType sizeType;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;
}
