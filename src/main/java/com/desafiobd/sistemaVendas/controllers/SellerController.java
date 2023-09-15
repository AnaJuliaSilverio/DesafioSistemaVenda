package com.desafiobd.sistemaVendas.controllers;

import com.desafiobd.sistemaVendas.dtos.SellerRequest;
import com.desafiobd.sistemaVendas.dtos.SellerResponse;
import com.desafiobd.sistemaVendas.services.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping
    public ResponseEntity<SellerResponse> createSeller(@RequestBody @Valid SellerRequest sellerRequest){
        SellerResponse sellerResponse = sellerService.createSeller(sellerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerResponse);
    }
    @GetMapping
    public ResponseEntity<List<SellerResponse>> getAllSeller(){
        List<SellerResponse> allSellers = sellerService.getAllSellers();
        return ResponseEntity.ok(allSellers);
    }
    @GetMapping("/salarys")
    public ResponseEntity<List<SellerResponse>> getSalarys(){
        List<SellerResponse> salarys = sellerService.getSalarys();
        return ResponseEntity.ok(salarys);
    }
}
