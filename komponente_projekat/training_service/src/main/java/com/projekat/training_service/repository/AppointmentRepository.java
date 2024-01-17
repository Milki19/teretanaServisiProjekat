package com.projekat.training_service.repository;

import com.projekat.training_service.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findAppointmentsByGymTrainingGymId(Long id);
    @Query("SELECT a FROM Appointment a WHERE a.training.gym.id = :gymId AND a.date BETWEEN :startDate AND :endDate")
    Optional<Appointment> findAppointmentsForNextFiveDays(
            @Param("gymId") Long gymId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
