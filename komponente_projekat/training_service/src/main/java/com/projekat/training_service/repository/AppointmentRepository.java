package com.projekat.training_service.repository;

import com.projekat.training_service.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentsByGymTrainingGymId(Long id);
    List<Appointment> findAppointmentsByDateBefore(LocalDate date);
}
