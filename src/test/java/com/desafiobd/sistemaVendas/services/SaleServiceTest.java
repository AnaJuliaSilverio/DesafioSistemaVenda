package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.SaleProductRequest;
import com.desafiobd.sistemaVendas.dtos.SaleRequest;
import com.desafiobd.sistemaVendas.dtos.SaleResponse;
import com.desafiobd.sistemaVendas.models.*;
import com.desafiobd.sistemaVendas.repositories.ClientRepository;
import com.desafiobd.sistemaVendas.repositories.SaleRepository;
import com.desafiobd.sistemaVendas.repositories.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.ManyToMany;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private SalesProductsService salesProductsService;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private SaleService saleService;

    @Test
    void whenCreateASaleShouldReturnSale(){
        HashSet<SaleProductRequest> saleProductRequests = new HashSet<>();
        saleProductRequests.add(new SaleProductRequest(1L,2));
        SaleRequest saleRequest=new SaleRequest(saleProductRequests);

        Client client = new Client(1L,"Ana","1111111","ana@exemplo.com","senha","Av 123");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Seller seller = new Seller(1L,"Ana","1111111","ana@exemplo.com","senha",new BigDecimal("10000"));
        when(sellerRepository.findById(1L)).thenReturn(Optional.of(seller));

        SaleResponse saleResponse = saleService.createSale(saleRequest, 1L, 1L);

        assertEquals(client,saleResponse.getClient());
    }
    @Test
    void shouldThrowAnExceptionWhenInvalidClientId(){
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->saleService.createSale(any(SaleRequest.class), 1L, 1L));
    }

    @Test
    void shouldThrowAnExceptionWhenInvalidSellerId(){
        Client client = new Client(1L,"Ana","1111111","ana@exemplo.com","senha","Av 123");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        when(sellerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->saleService.createSale(any(SaleRequest.class), 1L, 1L));
    }

    @Test
    void shouldReturnTotalAmountOfAProductsSale(){
        Product product = new Product(1L,new BigDecimal("10000.00"),"Caneta",2);
        Product product1 = new Product(2L,new BigDecimal("12000.00"),"Lapis",2);
        HashSet<SalesProducts> salesProducts = new HashSet<>();
        salesProducts.add(new SalesProducts(1L,product,new Sale(),1));
        salesProducts.add(new SalesProducts(2L,product1,new Sale(),1));

        BigDecimal total = saleService.calculateTotalSaleAmount(salesProducts);
        assertEquals(total,new BigDecimal("22000.00"));
    }
    @Test
    void shouldReturnAListOfStrings(){
        List<String> productsName = new ArrayList<>();
        productsName.add("Ana");
        productsName.add("Julia");
        when(saleRepository.productOver10()).thenReturn(productsName);
        List<String> products = saleService.productsOver10();
        assertEquals(productsName,products);

    }
    @Test
    void shouldReturnAllSales(){
        HashSet<SalesProducts> salesProducts = new HashSet<>();
        Sale sale = new Sale(1L, LocalDate.now(), new BigDecimal("100"), new Client(), new Seller(), salesProducts);
        when(saleRepository.findAll()).thenReturn(Collections.singletonList(sale));
        List<SaleResponse> allSales = saleService.getAllSales();

        assertEquals(1L,allSales.get(0).getId());


    }
}