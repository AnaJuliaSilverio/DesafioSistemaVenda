package com.desafiobd.sistemaVendas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "sale")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sale_date",nullable = false)
    private LocalDate saleDate;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @ManyToOne
    @JoinColumn(name ="client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name ="seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SalesProducts> saleProducts;

    public Long getId() {
        return id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Client getClient() {
        return client;
    }

    public Seller getSeller() {
        return seller;
    }
    @JsonManagedReference
    public Set<SalesProducts> getSaleProducts() {
        return saleProducts;
    }
}
