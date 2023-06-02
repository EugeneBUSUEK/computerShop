package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.persist.db.h2.entity.DesktopDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DesktopDetailRepository extends CrudRepository<DesktopDetailEntity, Long> {
    Optional<DesktopDetailEntity> findByProductEntityId(Long productEntity_id);
}
