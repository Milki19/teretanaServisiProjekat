package com.projekat.training_service.controller;

import com.projekat.training_service.dto.AppointmentCreateDto;
import com.projekat.training_service.dto.AppointmentDto;
import com.projekat.training_service.security.CheckSecurity;
import com.projekat.training_service.service.appointment.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //    @ApiOperation(value = "Get all appointments")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                    value = "Sorting criteria in the format: property(,asc|desc). " +
//                            "Default sort order is ascending. " +
//                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<List<AppointmentDto>> getAll(@RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<AppointmentDto> getAppointment(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(appointmentService.findById(id), HttpStatus.OK);
    }

//    @GetMapping("/gym/{id}")
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_CLIENT", "ROLE_MANAGER"})
//    public ResponseEntity<List<AppointmentDto>> getAppointmentsByGymID(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
//        return new ResponseEntity<>(appointmentService.findByGymId(id), HttpStatus.OK);
//    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<AppointmentDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid AppointmentCreateDto appointmentCreateDto){
        return new ResponseEntity<>(appointmentService.add(appointmentCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        appointmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
