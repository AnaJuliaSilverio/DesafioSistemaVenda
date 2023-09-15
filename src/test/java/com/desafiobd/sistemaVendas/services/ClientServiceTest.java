package com.desafiobd.sistemaVendas.services;

import com.desafiobd.sistemaVendas.dtos.ClientRequest;
import com.desafiobd.sistemaVendas.dtos.ClientResponse;
import com.desafiobd.sistemaVendas.models.Client;
import com.desafiobd.sistemaVendas.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private ClientService clientService;

    @Test
    void whenCreateAClientShouldReturnClient(){
        ClientRequest clientRequest = new ClientRequest("Ana","1111111","ana@exemplo.com","senha","Av 123");
        Client client = new Client(1L,"Ana","1111111","ana@exemplo.com","senha","Av 123");
        ClientResponse clientResponse = clientService.createClient(clientRequest);

        assertEquals(client.getCpf(),clientResponse.getCpf());
        assertEquals(client.getAddress(),clientResponse.getAddress());
    }
    @Test
    void shouldReturnAllClients(){
        Client client = new Client(1L,"Ana","1111111","ana@exemplo.com","senha","Av 123");
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));
        List<ClientResponse> allClients = clientService.getAllClients();

        assertEquals("Ana",allClients.get(0).getName());
    }

    @Test
    void shouldDeleteAClient(){
        Client client = new Client(1L,"Ana","1111111","ana@exemplo.com","senha","Av 123");
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        clientService.deleteClient(1L);
        verify(clientRepository,times(1)).delete(client);
    }
    @Test
    void shouldThrowAnExceptionWhenIdInvalid(){
        Client client = new Client(1L,"Ana","1111111","ana@exemplo.com","senha","Av 123");
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->  clientService.deleteClient(1L));

        verify(clientRepository,never()).delete(client);
    }
    @Test
    void shouldReturnCountOfEmailEndingWithZup(){
        when(clientRepository.countUsersWithEmailEndingWithZup()).thenReturn(1L);

        Long count = clientService.countUsersWithEmailEndingWithZup();

        assertEquals(count,1L);
        verify(clientRepository,times(1)).countUsersWithEmailEndingWithZup();
    }
}