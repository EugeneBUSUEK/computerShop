package com.hh.computerShop.controller;

import com.hh.computerShop.model.request.NotebookRequest;
import com.hh.computerShop.model.response.NotebookResponse;
import com.hh.computerShop.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products/notebooks")
@RequiredArgsConstructor
public class NotebookController {
    private final NotebookService notebookService;

    @PostMapping()
    public ResponseEntity<?> createNotebook(@RequestBody NotebookRequest notebookRequest) {
        NotebookResponse notebookResponse = notebookService.createNotebook(notebookRequest);

        return new ResponseEntity<>(notebookResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateNotebook(@RequestBody NotebookRequest notebookRequest) {
        NotebookResponse notebookResponse = notebookService.updateNotebook(notebookRequest);

        return new ResponseEntity<>(notebookResponse, HttpStatus.OK);
    }
}
