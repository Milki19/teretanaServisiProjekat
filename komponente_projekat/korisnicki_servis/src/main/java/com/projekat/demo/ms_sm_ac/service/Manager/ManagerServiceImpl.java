package com.projekat.demo.ms_sm_ac.service.Manager;

import com.projekat.demo.ms_sm_ac.domain.Manager;
import com.projekat.demo.ms_sm_ac.dto.*;
import com.projekat.demo.ms_sm_ac.exception.NotFoundException;
import com.projekat.demo.ms_sm_ac.mapper.ManagerMapper;
import com.projekat.demo.ms_sm_ac.repository.ManagerRepository;
import com.projekat.demo.ms_sm_ac.service.Token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;
    private TokenService tokenService;

    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerMapper managerMapper, TokenService tokenService) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.tokenService = tokenService;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) throws Exception {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepository
                .findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        claims.put("role", manager.getUserType());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ManagerDto findById(Long id) {
        return managerRepository.findById(id)
                .map(managerMapper::managerToManagerDto)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }
}
