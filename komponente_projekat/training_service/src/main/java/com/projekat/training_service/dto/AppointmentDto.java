package com.projekat.training_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private String startTime;
    private String endTime;
    @JsonProperty("training")
    private TrainingDto trainingDto;
    private Integer capacity;
}
