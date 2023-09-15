package com.desafiobd.sistemaVendas.controllers;

import com.desafiobd.sistemaVendas.dtos.ClientRequest;

import com.desafiobd.sistemaVendas.dtos.ClientResponse;
import com.desafiobd.sistemaVendas.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody @Valid ClientRequest clientRequest){
        ClientResponse clientResponse = clientService.createClient(clientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients(){
        List<ClientResponse> allClients = clientService.getAllClients();
        return ResponseEntity.ok(allClients);
    }
    @GetMapping("/zup-email")
    public ResponseEntity<Long> countUsersWithEmailEndingWithZup(){
        Long count = clientService.countUsersWithEmailEndingWithZup();
        return ResponseEntity.ok(count);
    }
    @DeleteMapping("{idclient}")
    public void deleteClient(@PathVariable(value = "idclient") Long idCliente){
        clientService.deleteClient(idCliente);
    }
}
