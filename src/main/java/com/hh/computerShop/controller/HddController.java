package com.hh.computerShop.controller;

import com.hh.computerShop.model.request.HddRequest;
import com.hh.computerShop.model.response.HddResponse;
import com.hh.computerShop.service.HddService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products/hdds")
@RequiredArgsConstructor
public class HddController {
    private final HddService hddService;

    @PostMapping()
    public ResponseEntity<?> createHdd(@RequestBody HddRequest hddRequest) {
        HddResponse hddResponse = hddService.createHdd(hddRequest);

        return new ResponseEntity<>(hddResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateHdd(@RequestBody HddRequest hddRequest) {
        HddResponse hddResponse = hddService.updateHdd(hddRequest);

        return new ResponseEntity<>(hddResponse, HttpStatus.OK);
    }
}
