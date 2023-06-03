package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.persist.db.h2.entity.MonitorDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MonitorDetailRepository extends CrudRepository<MonitorDetailEntity, Long> {
    Optional<MonitorDetailEntity> findByProductEntityId(Long productEntity_id);
}
