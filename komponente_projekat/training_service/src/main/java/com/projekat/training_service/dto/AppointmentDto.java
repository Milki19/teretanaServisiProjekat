package com.projekat.training_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private String startTime;
    private String endTime;
    @JsonProperty("gymTraining")
    private TrainingDto gymTrainingDto;
    private Integer capacity;
}
