package com.projekat.training_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TrainingDto {

    private Long id;
    @JsonProperty("gymnasium")
    private GymnasiumDto gymnasiumDto;
    @JsonProperty("trainingType")
    private TrainingTypeDto trainingTypeDto;
    private BigDecimal price;

}
