package com.hh.computerShop.controller;

import com.hh.computerShop.model.request.DesktopRequest;
import com.hh.computerShop.model.response.DesktopResponse;
import com.hh.computerShop.service.DesktopService;
import com.hh.computerShop.service.impl.DesktopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products/desktops")
@RequiredArgsConstructor
public class DesktopController {
    private final DesktopService desktopService;

    @PostMapping()
    public ResponseEntity<?> creatDesktop(@RequestBody DesktopRequest desktopRequest) {
        DesktopResponse desktopResponse = desktopService.createDesktop(desktopRequest);

        return new ResponseEntity<>(desktopResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateDesktop(@RequestBody DesktopRequest desktopRequest) {
        DesktopResponse desktopResponse = desktopService.updateDesktop(desktopRequest);

        return new ResponseEntity<>(desktopResponse, HttpStatus.OK);
    }
}
