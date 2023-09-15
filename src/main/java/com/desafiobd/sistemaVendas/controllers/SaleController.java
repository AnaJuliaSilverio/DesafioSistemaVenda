package com.desafiobd.sistemaVendas.controllers;

import com.desafiobd.sistemaVendas.dtos.SaleRequest;
import com.desafiobd.sistemaVendas.dtos.SaleResponse;
import com.desafiobd.sistemaVendas.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/{clientId}/{sellerId}")
    public ResponseEntity<SaleResponse> createSale(@RequestBody @Valid SaleRequest saleRequest
            , @PathVariable(value = "clientId") Long clientId,@PathVariable(value = "sellerId") Long sellerId){

        SaleResponse saleResponse = saleService.createSale(saleRequest, clientId, sellerId);

        return ResponseEntity.ok(saleResponse);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales(){
        List<SaleResponse> allSales = saleService.getAllSales();
        return ResponseEntity.ok(allSales);
    }
    @GetMapping("over-ten")
    public ResponseEntity<List<String>> productsOver10(){
        List<String> products = saleService.productsOver10();
        return ResponseEntity.ok(products);
    }
}
