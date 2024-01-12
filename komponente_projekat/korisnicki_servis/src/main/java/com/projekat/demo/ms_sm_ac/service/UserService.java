package com.projekat.demo.ms_sm_ac.service;

import com.projekat.demo.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.demo.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.demo.ms_sm_ac.dto.UserCreateDto;
import com.projekat.demo.ms_sm_ac.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    UserDto add(UserCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

}
