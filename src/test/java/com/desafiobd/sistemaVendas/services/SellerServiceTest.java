package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.ClientRequest;
import com.desafiobd.sistemaVendas.dtos.ClientResponse;
import com.desafiobd.sistemaVendas.dtos.SellerRequest;
import com.desafiobd.sistemaVendas.dtos.SellerResponse;
import com.desafiobd.sistemaVendas.models.Client;
import com.desafiobd.sistemaVendas.models.Seller;
import com.desafiobd.sistemaVendas.repositories.SellerRepository;
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
class SellerServiceTest {
    @Mock
    private SellerRepository sellerRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private SellerService sellerService;

    @Test
    void whenCreateASellerShouldReturnSeller(){
        SellerRequest sellerRequest = new SellerRequest("Ana","1111111","ana@exemplo.com","senha",new BigDecimal("10000"));
        SellerResponse sellerResponse = sellerService.createSeller(sellerRequest);

        assertEquals("1111111",sellerResponse.getCpf());
        assertEquals("ana@exemplo.com",sellerResponse.getEmail());
    }
    @Test
    void shouldReturnAllSeller(){
        Seller seller = new Seller(1L,"Ana","1111111","ana@exemplo.com","senha",new BigDecimal("10000"));
        when(sellerRepository.findAll()).thenReturn(Collections.singletonList(seller));
        List<SellerResponse> allSellers = sellerService.getAllSellers();
        assertEquals("Ana",allSellers.get(0).getName());
    }
    @Test
    void shouldReturnAllSalarys(){
        Seller seller = new Seller(1L,"Ana","1111111","ana@exemplo.com","senha",new BigDecimal("10000"));
        when(sellerRepository.findSalarys()).thenReturn(Collections.singletonList(seller));
        List<SellerResponse> allSellers = sellerService.getSalarys();
        assertEquals("Ana",allSellers.get(0).getName());
    }
}