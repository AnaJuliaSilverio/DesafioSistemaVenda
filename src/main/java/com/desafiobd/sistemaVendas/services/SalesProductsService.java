package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.SaleProductRequest;
import com.desafiobd.sistemaVendas.dtos.SaleProductResponse;
import com.desafiobd.sistemaVendas.dtos.SaleRequest;
import com.desafiobd.sistemaVendas.dtos.SaleResponse;
import com.desafiobd.sistemaVendas.models.Product;
import com.desafiobd.sistemaVendas.models.Sale;
import com.desafiobd.sistemaVendas.models.SalesProducts;
import com.desafiobd.sistemaVendas.repositories.ProductRepository;
import com.desafiobd.sistemaVendas.repositories.SalesProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalesProductsService {
    @Autowired
    private SalesProductRepository salesProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public SalesProducts createSaleProduct(SaleProductRequest saleProductRequest, Sale sale){
        Product product = productRepository.findById(saleProductRequest.getIdProduct()).orElseThrow(()->new EntityNotFoundException("Produto não encontrado"));
        checkAvailableQuantity(product,saleProductRequest.getQuantitySold());

        SalesProducts salesProducts = new SalesProducts();
        salesProducts.setSale(sale);
        salesProducts.setProduct(product);
        salesProducts.setQuantitySold(saleProductRequest.getQuantitySold());
        return salesProducts;

    }
    public Set<SalesProducts> setSalesProducts(Set<SaleProductRequest> saleProductRequests,Sale sale){
        return saleProductRequests.stream()
                .map(saleProductRequest -> createSaleProduct(saleProductRequest,sale))
                .collect(Collectors.toSet());
    }
    public void checkAvailableQuantity(Product product,int quantity){
        if (product.getQuantity()>=quantity){
            product.setQuantity(product.getQuantity()-quantity);
        }else throw new IllegalArgumentException("Quantidade indisponível");
    }


}
