package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.persist.db.h2.entity.DetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetailRepository extends CrudRepository<DetailEntity, Long> {
    List<DetailEntity> findAllByProductEntityId(Long productEntity_id);
}
