package com.projekat.training_service.service.appointment;

import com.projekat.training_service.dto.AppointmentCreateDto;
import com.projekat.training_service.dto.AppointmentDto;

import java.util.List;
import java.util.Optional;


public interface AppointmentService {

    List<AppointmentDto> findAll();
    AppointmentDto findById(Long id);
    Optional<AppointmentDto> findByGymId(Long id);
    AppointmentDto add(AppointmentCreateDto appointmentCreateDto);
    void deleteById(Long id);

}
