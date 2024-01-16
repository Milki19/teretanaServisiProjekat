package com.projekat.training_service.service.reservation;

import com.projekat.training_service.dto.ReservationCreateDto;
import com.projekat.training_service.dto.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    Page<ReservationDto> findAll(Pageable pageable);
    void addReservation(ReservationCreateDto reservationCreateDto);
    void deleteById(Long id);
}
