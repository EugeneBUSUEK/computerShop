package com.hh.computerShop.service;

import com.hh.computerShop.model.request.HddRequest;
import com.hh.computerShop.model.response.HddResponse;

public interface HddService {
    HddResponse createHdd(HddRequest hddRequest);

    HddResponse updateHdd(HddRequest hddRequest);
}
