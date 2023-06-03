package com.hh.computerShop.service;

import com.hh.computerShop.model.request.MonitorRequest;
import com.hh.computerShop.model.response.MonitorResponse;

public interface MonitorService {
    MonitorResponse createMonitor(MonitorRequest monitorRequest);

    MonitorResponse updateMonitor(MonitorRequest monitorRequest);
}
