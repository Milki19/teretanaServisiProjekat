package com.projekat.demo.ms_sm_ac.controller;

import com.projekat.demo.ms_sm_ac.dto.TokenRequestDto;
import com.projekat.demo.ms_sm_ac.dto.TokenResponseDto;
import com.projekat.demo.ms_sm_ac.dto.UserCreateDto;
import com.projekat.demo.ms_sm_ac.dto.UserDto;
import com.projekat.demo.ms_sm_ac.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

//    GLUPOST NEKA CRVENI SE
//        public UserController(UserService userService1) {
//        this.userService = userService1;
//    }


    //PRILAGODITI NASEM NACINU ROLOVANJA
//    @GetMapping
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
//    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
//                                                     Pageable pageable) {
//
//        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
//    }

    @ApiOperation(value = "Register user")
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.add(userCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

}
