package com.projekat.notification_service.service;

import com.projekat.notification_service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService{
    void sendActivationEmail(ActivationDto activationDto) throws InterruptedException;
    void sendPasswordResetEmail(UserDto userDto) throws InterruptedException;
    void sendSuccessfulReservationNotification(ReservationNotificationDto reservationNotificationDto) throws InterruptedException;
    void sendAppointmentCancellationNotification(NotificationCancellationDto notificationCancellationDto);
    void sendReservationCancellationNotification(NotificationCancellationDto notificationCancellationDto);
    void sendReminderNotification(ReservationNotificationDto reservationNotificationDto);

    Page<NotificationDto> getAllNotificationsForAdmin(Pageable pageable);

}
