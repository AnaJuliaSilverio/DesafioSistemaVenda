package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.SellerRequest;
import com.desafiobd.sistemaVendas.dtos.SellerResponse;
import com.desafiobd.sistemaVendas.models.Seller;
import com.desafiobd.sistemaVendas.repositories.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SellerResponse createSeller(SellerRequest sellerRequest){
        Seller seller = new Seller();
        modelMapper.map(sellerRequest,seller);
        sellerRepository.save(seller);
        return modelMapper.map(seller,SellerResponse.class);
    }
    public List<SellerResponse> getAllSellers(){
        return sellerRepository.findAll().stream()
                .map(seller -> modelMapper.map(seller,SellerResponse.class)).toList();
    }
    public List<SellerResponse> getSalarys(){
        return sellerRepository.findSalarys().stream()
                .map(seller -> modelMapper.map(seller,SellerResponse.class)).toList();
    }
}
