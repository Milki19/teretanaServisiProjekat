package com.projekat.training_service.repository;

import com.projekat.training_service.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAppointmentId(Long id);

}
