package com.projekat.notification_service.controller;

import com.projekat.notification_service.dto.NotificationDto;
import com.projekat.notification_service.security.CheckSecurity;
import com.projekat.notification_service.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/admin")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationDto>> getAllNotifications(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(notificationService.getAllNotificationsForAdmin(pageable), HttpStatus.OK);
    }

}