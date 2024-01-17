package com.projekat.training_service.mapper;

import com.projekat.training_service.domain.Reservation;
import com.projekat.training_service.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    private AppointmentMapper appointmentMapper;

    public ReservationMapper(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    public ReservationDto reservationToReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUserId());
        reservationDto.setPrice(reservation.getPrice());
        reservationDto.setAppointment(appointmentMapper.appointmentToAppointmentDto(reservation.getAppointment()));
        return reservationDto;
    }
}
