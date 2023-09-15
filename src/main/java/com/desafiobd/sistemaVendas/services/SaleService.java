package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.SaleProductRequest;
import com.desafiobd.sistemaVendas.dtos.SaleRequest;
import com.desafiobd.sistemaVendas.dtos.SaleResponse;
import com.desafiobd.sistemaVendas.models.*;
import com.desafiobd.sistemaVendas.repositories.ClientRepository;
import com.desafiobd.sistemaVendas.repositories.SaleRepository;
import com.desafiobd.sistemaVendas.repositories.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SalesProductsService salesProductsService;
    @Autowired
    private ModelMapper modelMapper;

  public SaleResponse createSale(SaleRequest saleRequest,Long clientId,Long sellerId){
      Client client = clientRepository.findById(clientId).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
      Seller seller = sellerRepository.findById(sellerId).orElseThrow(()->new EntityNotFoundException("Vendedor não encontrado"));
      Sale sale = new Sale();

      sale.setSaleDate(LocalDate.now());
      sale.setClient(client);
      sale.setSeller(seller);
      Set<SalesProducts> salesProductsSet =salesProductsService.setSalesProducts(saleRequest.getSaleProducts(),sale);
      sale.setTotalAmount(calculateTotalSaleAmount(salesProductsSet));
      sale.setSaleProducts(salesProductsSet);
      saleRepository.save(sale);
      return modelMapper.map(sale, SaleResponse.class);
  }


  public List<SaleResponse> getAllSales(){
      return saleRepository.findAll().stream()
              .map(sale -> modelMapper.map(sale, SaleResponse.class)).toList();
  }
  public List<String> productsOver10(){
      return saleRepository.productOver10();
  }
    public BigDecimal calculateTotalSaleAmount(Set<SalesProducts> salesProducts) {
        return salesProducts.stream()
                .map(sp -> sp.getProduct().getPrice().multiply(BigDecimal.valueOf(sp.getQuantitySold())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
