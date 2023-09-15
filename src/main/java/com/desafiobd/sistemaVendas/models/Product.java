package com.desafiobd.sistemaVendas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_product")
    private Long codProduct;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String name;
    @Column(name = "quantity")
    private Integer quantity;

}
