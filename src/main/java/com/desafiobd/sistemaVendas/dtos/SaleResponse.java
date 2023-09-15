package com.desafiobd.sistemaVendas.dtos;

import com.desafiobd.sistemaVendas.models.Client;
import com.desafiobd.sistemaVendas.models.Product;
import com.desafiobd.sistemaVendas.models.SalesProducts;
import com.desafiobd.sistemaVendas.models.Seller;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    @NotNull
    private Long id;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate saleDate;
    @Positive
    private BigDecimal totalAmount;
    private Client client;
    private Seller seller;
    @NotNull
    private Set<SalesProducts> saleProducts;
}
