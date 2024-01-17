package com.projekat.notification_service.ms_sm_ac.controller;

import com.projekat.notification_service.ms_sm_ac.dto.ClientCreateDto;
import com.projekat.notification_service.ms_sm_ac.dto.ClientDto;
import com.projekat.notification_service.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.notification_service.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.notification_service.ms_sm_ac.service.Client.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

//    GLUPOST NEKA CRVENI SE
        public ClientController(ClientService clientService1) {
        this.clientService = clientService1;
    }


    //PRILAGODITI NASEM NACINU ROLOVANJA
//    @GetMapping
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
//    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                     Pageable pageable) {
//
//        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
//    }

//    @ApiOperation(value = "Register user")
//    @PostMapping
//    public ResponseEntity<ClientDto> saveUser(@RequestBody @Valid ClientCreateDto userCreateDto) {
//        return new ResponseEntity<>(clientService.add(userCreateDto), HttpStatus.CREATED);
//    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(@ApiIgnore Pageable pageable){
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> add(@RequestBody @Valid ClientCreateDto clientCreateDto) throws Exception {
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Dodati jos ako treba

}
