package com.projekat.training_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppointmentCreateDto {
    private LocalDate date;
    private String startTime;
    private String endTime;
    private Long gymTraining;
}
