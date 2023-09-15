package com.desafiobd.sistemaVendas.dtos;

import com.desafiobd.sistemaVendas.models.Product;
import com.desafiobd.sistemaVendas.models.Sale;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleProductResponse {
    @NotNull
    private Product product;
    @NotNull
    private Sale sale;
    private int quantitySold;
}
