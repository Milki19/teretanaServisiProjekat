package com.projekat.notification_service.ms_sm_ac.service.Client;

import com.projekat.notification_service.ms_sm_ac.domain.Client;
import com.projekat.notification_service.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.notification_service.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.notification_service.ms_sm_ac.dto.ClientCreateDto;
import com.projekat.notification_service.ms_sm_ac.dto.ClientDto;
import com.projekat.notification_service.ms_sm_ac.exception.NotFoundException;
import com.projekat.notification_service.ms_sm_ac.mapper.ClientMapper;
import com.projekat.notification_service.ms_sm_ac.repository.ClientRepository;
import com.projekat.notification_service.ms_sm_ac.service.Token.TokenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private TokenService tokenService;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, TokenService tokenService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.tokenService = tokenService;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::clientToClientDto);
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) throws Exception {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Client client = clientRepository
                .findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", client.getId());
        claims.put("role", client.getUserType());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ClientDto findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::clientToClientDto)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
