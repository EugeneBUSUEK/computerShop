package com.hh.computerShop.controller;

import com.hh.computerShop.model.request.MonitorRequest;
import com.hh.computerShop.model.response.MonitorResponse;
import com.hh.computerShop.service.MonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products/monitors")
@RequiredArgsConstructor
public class MonitorController {
    private final MonitorService monitorService;

    @PostMapping()
    private ResponseEntity<?> createMonitor(@RequestBody MonitorRequest monitorRequest) {
        MonitorResponse monitorResponse = monitorService.createMonitor(monitorRequest);

        return new ResponseEntity<>(monitorResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateMonitor(@RequestBody MonitorRequest monitorRequest) {
        MonitorResponse monitorResponse = monitorService.updateMonitor(monitorRequest);

        return new ResponseEntity<>(monitorResponse, HttpStatus.OK);
    }
}
