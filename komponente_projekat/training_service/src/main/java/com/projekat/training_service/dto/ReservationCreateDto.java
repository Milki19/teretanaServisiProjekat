package com.projekat.training_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationCreateDto {
    private Long appointmentId;
    private Long userId;
}
