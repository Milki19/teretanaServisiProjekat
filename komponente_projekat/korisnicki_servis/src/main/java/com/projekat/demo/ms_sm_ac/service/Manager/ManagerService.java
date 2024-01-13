package com.projekat.demo.ms_sm_ac.service.Manager;

import com.projekat.demo.ms_sm_ac.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {

    
    Page<ManagerDto> findAll(Pageable pageable);

    ManagerDto add(ManagerCreateDto managerCreateDto) throws Exception;

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ManagerDto findById(Long id);

    void deleteById(Long id);
}
