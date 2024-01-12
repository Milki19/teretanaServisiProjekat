package com.projekat.demo.ms_sm_ac.mapper;

import com.projekat.demo.ms_sm_ac.domain.Client;
import com.projekat.demo.ms_sm_ac.dto.ClientCreateDto;
import com.projekat.demo.ms_sm_ac.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientMapper() {
    }

    public ClientDto clientToClientDto(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setUserType(client.getUserType());
        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto){
        Client client = new Client();
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUserName());
        client.setBirthDate(clientCreateDto.getBirthDate());
        client.setEmail(clientCreateDto.getEmail());
        client.setPassword(clientCreateDto.getPassword());
        client.setUserType(clientCreateDto.getUserType());
        return client;
    }
}
