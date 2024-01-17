package com.projekat.notification_service.ms_sm_ac.service.Client;

import com.projekat.notification_service.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.notification_service.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.notification_service.ms_sm_ac.dto.ClientCreateDto;
import com.projekat.notification_service.ms_sm_ac.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    
    Page<ClientDto> findAll(Pageable pageable);

    ClientDto add(ClientCreateDto userCreateDto) throws Exception;

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ClientDto findById(Long id);

    void deleteById(Long id);
}
