package com.projekat.training_service.dto;

import com.projekat.training_service.domain.TrainingType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymnasiumDto {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfCoaches;
    private Integer capacity;
}
