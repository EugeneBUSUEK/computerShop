package com.hh.computerShop.persist.db.h2;

import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.persist.db.h2.entity.DesktopDetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface DesktopDetailRepository extends CrudRepository<DesktopDetailEntity, Long> {
}
