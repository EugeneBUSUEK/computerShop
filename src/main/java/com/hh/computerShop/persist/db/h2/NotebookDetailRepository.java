package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.persist.db.h2.entity.NotebookDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotebookDetailRepository extends CrudRepository<NotebookDetailEntity, Long> {
    Optional<NotebookDetailEntity> findByProductEntityId(Long productEntity_id);
}
