package com.desafiobd.sistemaVendas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "seller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String cpf;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,length = 15)
    private String password;
    @Column(nullable = false)
    private BigDecimal salary;

}
