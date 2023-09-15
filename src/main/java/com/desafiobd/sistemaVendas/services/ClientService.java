package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.ClientRequest;
import com.desafiobd.sistemaVendas.dtos.ClientResponse;
import com.desafiobd.sistemaVendas.models.Client;
import com.desafiobd.sistemaVendas.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ClientResponse createClient(ClientRequest clientRequest){
        Client client = new Client();
        modelMapper.map(clientRequest,client);
        clientRepository.save(client);
        return modelMapper.map(client, ClientResponse.class);
    }
    public List<ClientResponse> getAllClients(){
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client,ClientResponse.class)).toList();
    }
    public void deleteClient(Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
        clientRepository.delete(client);
    }
    public Long countUsersWithEmailEndingWithZup(){
        return clientRepository.countUsersWithEmailEndingWithZup();
    }
}
