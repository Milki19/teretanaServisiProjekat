package com.projekat.training_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymnasiumCreateDto {

    private String name;
    private String description;
    private Integer numberOfCoaches;
    private Integer capacity;
}
