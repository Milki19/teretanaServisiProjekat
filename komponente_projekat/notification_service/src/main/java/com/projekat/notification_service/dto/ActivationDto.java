package com.projekat.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationDto {
    private String mail;
    private String firstName;
    private String lastName;
    private String token;
}
