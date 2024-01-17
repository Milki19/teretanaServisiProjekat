package com.projekat.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationCreateDto {
    private String type;
    private String recipientEmail;
}
