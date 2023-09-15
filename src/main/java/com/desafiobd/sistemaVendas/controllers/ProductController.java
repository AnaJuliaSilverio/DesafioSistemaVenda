package com.desafiobd.sistemaVendas.controllers;

import com.desafiobd.sistemaVendas.dtos.ProductRequest;
import com.desafiobd.sistemaVendas.dtos.ProductResponse;
import com.desafiobd.sistemaVendas.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest){
        ProductResponse productResponse = productService.createProduct(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
    @GetMapping("/update")
    public ResponseEntity<List<ProductResponse>> updateAllProductsWithZeroqtd(){
        List<ProductResponse> allProducts = productService.updateNullQuantitiesToZero();
        return ResponseEntity.ok(allProducts);
    }
}
