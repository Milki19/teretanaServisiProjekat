package com.projekat.demo.ms_sm_ac.service.impl;

import com.projekat.demo.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.demo.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.demo.ms_sm_ac.dto.UserCreateDto;
import com.projekat.demo.ms_sm_ac.dto.UserDto;
import com.projekat.demo.ms_sm_ac.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {




    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserDto add(UserCreateDto userCreateDto) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        return null;
    }
}
