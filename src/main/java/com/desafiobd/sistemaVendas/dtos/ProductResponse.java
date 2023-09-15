package com.desafiobd.sistemaVendas.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    @NotNull
    private Long codProduct;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotBlank
    private String name;
    private Integer quantity;

}
