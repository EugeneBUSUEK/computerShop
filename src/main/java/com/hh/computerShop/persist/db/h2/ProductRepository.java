package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByProductType(ProductType productType);
}
