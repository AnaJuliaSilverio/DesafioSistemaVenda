package com.desafiobd.sistemaVendas.repositories;

import com.desafiobd.sistemaVendas.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT COUNT(*) FROM Client c WHERE c.email LIKE '%zup.com.br'")
    Long countUsersWithEmailEndingWithZup();

}
