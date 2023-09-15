package com.desafiobd.sistemaVendas.repositories;

import com.desafiobd.sistemaVendas.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query("SELECT DISTINCT sp.product.name " +
            "FROM SalesProducts sp " +
            "WHERE sp.quantitySold * sp.product.price > 10")
    List<String> productOver10();

}
