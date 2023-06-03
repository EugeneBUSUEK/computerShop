package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.persist.db.h2.entity.HddDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HddDetailRepository extends CrudRepository<HddDetailEntity, Long> {
    Optional<HddDetailEntity> findByProductEntityId(Long productEntity_id);
}
