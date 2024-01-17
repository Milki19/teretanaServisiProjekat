package com.projekat.notification_service.ms_sm_ac.controller;

import com.projekat.notification_service.ms_sm_ac.dto.ManagerCreateDto;
import com.projekat.notification_service.ms_sm_ac.dto.ManagerDto;
import com.projekat.notification_service.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.notification_service.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.notification_service.ms_sm_ac.service.Manager.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService1) {
        this.managerService = managerService1;
    }

    @GetMapping
    public ResponseEntity<Page<ManagerDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ManagerDto> add(@RequestBody @Valid ManagerCreateDto managerCreateDto) throws Exception {
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        managerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }
}
