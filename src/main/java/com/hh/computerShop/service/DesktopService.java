package com.hh.computerShop.service;

import com.hh.computerShop.model.request.DesktopRequest;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.persist.db.h2.entity.ProductEntity;

import java.util.List;

public interface DesktopService {
    DesktopResponse createDesktop(DesktopRequest desktopRequest);
    DesktopResponse updateDesktop(DesktopRequest desktopRequest);
}
