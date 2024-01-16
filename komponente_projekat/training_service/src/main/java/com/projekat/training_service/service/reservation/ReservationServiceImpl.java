package com.projekat.training_service.service.reservation;

import com.projekat.training_service.dto.ReservationCreateDto;
import com.projekat.training_service.dto.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ReservationServiceImpl implements ReservationService{



    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void addReservation(ReservationCreateDto reservationCreateDto) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
