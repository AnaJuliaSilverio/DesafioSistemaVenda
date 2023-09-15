package com.desafiobd.sistemaVendas.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleProductRequest {
    @NotNull
    private Long idProduct;
    private int quantitySold;
}
