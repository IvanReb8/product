package com.liverpool.mx.product.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liverpool.mx.product.models.ProductResponseDto;
import com.liverpool.mx.product.services.IProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> findProducts(@RequestParam String query){
        return new ResponseEntity<>(service.searchProducts(query), HttpStatus.OK);
    }

}
