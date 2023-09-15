package com.desafiobd.sistemaVendas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "sales_products")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_cod")
    private Product product;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "quantity_sold", nullable = false)
    private int quantitySold;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
    @JsonBackReference
    public Sale getSale() {
        return sale;
    }

    public int getQuantitySold() {
        return quantitySold;
    }
}
