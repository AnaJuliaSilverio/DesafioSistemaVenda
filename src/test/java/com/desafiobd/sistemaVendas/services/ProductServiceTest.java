package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.ProductRequest;
import com.desafiobd.sistemaVendas.dtos.ProductResponse;
import com.desafiobd.sistemaVendas.models.Product;
import com.desafiobd.sistemaVendas.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void whenCreateAPrdouctShouldReturnProduct(){
        ProductRequest productRequest = new ProductRequest(new BigDecimal("10000.00"),"Caneta",2);
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        ProductResponse productResponse = productService.createProduct(productRequest);
        assertEquals(product.getName(),productResponse.getName());
    }
    @Test
    void shouldReturnAllProducts(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        List<ProductResponse> allProducts = productService.getAllProducts();
        assertEquals("Caneta",allProducts.get(0).getName());
    }

}