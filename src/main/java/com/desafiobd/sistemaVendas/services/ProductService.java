package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.ProductRequest;
import com.desafiobd.sistemaVendas.dtos.ProductResponse;
import com.desafiobd.sistemaVendas.models.Product;
import com.desafiobd.sistemaVendas.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product();
        modelMapper.map(productRequest,product);
        productRepository.save(product);
        return modelMapper.map(product,ProductResponse.class);
    }
    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product,ProductResponse.class)).toList();
    }
    public List<ProductResponse> updateNullQuantitiesToZero(){
        productRepository.updateNullQuantitiesToZero();
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product,ProductResponse.class)).toList();
    }
}
