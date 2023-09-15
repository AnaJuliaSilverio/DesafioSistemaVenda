package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.SaleProductRequest;
import com.desafiobd.sistemaVendas.models.*;
import com.desafiobd.sistemaVendas.repositories.ProductRepository;
import com.desafiobd.sistemaVendas.repositories.SalesProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesProductsServiceTest {
    @Mock
    private SalesProductRepository salesProductRepository;
    @Mock
    private ProductRepository productRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private SalesProductsService salesProductsService;

    @Test
    void shouldcheckAvailableQuantity(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        int quantity = 1;
        salesProductsService.checkAvailableQuantity(product,quantity);
        assertEquals(1,product.getQuantity());
    }
    @Test
    void shouldcheckEqualQuantity(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        int quantity = 2;
        salesProductsService.checkAvailableQuantity(product,quantity);
        assertEquals(0,product.getQuantity());
    }
    @Test
    void shouldThrowAnExceptionInvalidQuantity(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        int quantity = 5;
        assertThrows(IllegalArgumentException.class,()->salesProductsService.checkAvailableQuantity(product,quantity));
    }
    @Test
    void shouldReturnSalesProducts(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        Sale sale = new Sale();
        HashSet<SaleProductRequest> saleProductRequests = new HashSet<>();
        saleProductRequests.add(new SaleProductRequest(1L,2));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Set<SalesProducts> salesProductsSet = salesProductsService.setSalesProducts(saleProductRequests, sale);

        assertEquals(1,salesProductsSet.size());
    }
    @Test
    void shouldReturnSalesProduct(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        Sale sale = new Sale();
        SaleProductRequest saleProductRequest = new SaleProductRequest(1L, 2);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        SalesProducts saleProduct = salesProductsService.createSaleProduct(saleProductRequest, sale);

        assertEquals(1L,saleProduct.getProduct().getCodProduct());
    }

    @Test
    void shouldThrowAnExceptionWhenIdIsInvalid(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        Sale sale = new Sale();
        SaleProductRequest saleProductRequest = new SaleProductRequest(1L, 2);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->salesProductsService.createSaleProduct(saleProductRequest, sale));

    }

}