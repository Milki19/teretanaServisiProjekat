package com.projekat.demo.ms_sm_ac.controller;

import com.projekat.demo.ms_sm_ac.dto.ClientCreateDto;
import com.projekat.demo.ms_sm_ac.dto.ClientDto;
import com.projekat.demo.ms_sm_ac.service.Client.ClientService;
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
    private ClientService clientService;

    public ManagerController(ClientService clientService1) {
        this.clientService = clientService1;
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
    public ResponseEntity<ClientDto> add(@RequestBody @Valid ClientCreateDto clientCreateDto){
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
