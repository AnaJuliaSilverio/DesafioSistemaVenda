package com.desafiobd.sistemaVendas.repositories;

import com.desafiobd.sistemaVendas.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    @Query("SELECT s FROM Seller s " +
            "ORDER BY s.salary DESC")
    List<Seller> findSalarys();
}
