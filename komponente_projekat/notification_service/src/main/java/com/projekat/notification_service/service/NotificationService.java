package com.projekat.notification_service.service;

import com.projekat.notification_service.dto.ActivationDto;
import com.projekat.notification_service.dto.NotificationDto;
import com.projekat.notification_service.dto.ReservationNotificationDto;
import com.projekat.notification_service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService{
    void sendActivationEmail(ActivationDto activationDto) throws InterruptedException;
    void sendPasswordResetEmail(UserDto userDto);
    void sendSuccessfulReservationNotification(ReservationNotificationDto reservationNotificationDto);
    void sendCancellationNotification(ReservationNotificationDto reservationNotificationDto);
    void sendReminderNotification(ReservationNotificationDto reservationNotificationDto);

    Page<NotificationDto> getAllNotificationsForAdmin(Pageable pageable);
    Page<NotificationDto> getClientNotifications(Long clientId, Pageable pageable);
    Page<NotificationDto> getManagerNotifications(Long managerId, Pageable pageable);
//    List<Notification> filterNotificationsByTypeAndDate(String type, String email, Date startDate, Date endDate);
}
