package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.persist.db.h2.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
