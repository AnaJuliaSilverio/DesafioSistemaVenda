package com.desafiobd.sistemaVendas.repositories;

import com.desafiobd.sistemaVendas.models.SalesProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesProductRepository extends JpaRepository<SalesProducts,Long> {
}
