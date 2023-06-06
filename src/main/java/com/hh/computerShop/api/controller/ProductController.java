package com.hh.computerShop.api.controller;

import com.hh.computerShop.model.enums.ProductType;
import com.hh.computerShop.model.request.ProductRequest;
import com.hh.computerShop.model.response.ProductResponse;
import com.hh.computerShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<?> creatDesktop(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);

        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(productRequest);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "productId") Long id) {
        ProductResponse productResponse = productService.getProductById(id);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProductsByProductType(@RequestParam(name = "productType") ProductType productType) {
        List<ProductResponse> productResponse = productService.getAllProductsByProductType(productType);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
