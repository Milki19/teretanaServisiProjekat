package com.projekat.training_service.controller;

import com.projekat.training_service.dto.GymnasiumCreateDto;
import com.projekat.training_service.dto.GymnasiumDto;
import com.projekat.training_service.security.CheckSecurity;
import com.projekat.training_service.service.gymnasium.GymnasiumService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/gymnasium")
public class GymnasiumController {
    private GymnasiumService gymnasiumService;

    public GymnasiumController(GymnasiumService gymnasiumService) {
        this.gymnasiumService = gymnasiumService;
    }

    @ApiOperation(value = "Get all gyms")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<Page<GymnasiumDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(gymnasiumService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<GymnasiumDto> getGym(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(gymnasiumService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<GymnasiumDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid GymnasiumCreateDto gymCreateDto){
        return new ResponseEntity<>(gymnasiumService.add(gymCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        gymnasiumService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
