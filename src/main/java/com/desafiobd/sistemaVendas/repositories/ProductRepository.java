package com.desafiobd.sistemaVendas.repositories;

import com.desafiobd.sistemaVendas.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.quantity = COALESCE(p.quantity, 0) WHERE p.quantity IS NULL")
    void updateNullQuantitiesToZero();
}
