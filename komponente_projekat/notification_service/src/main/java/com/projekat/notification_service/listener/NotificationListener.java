package com.projekat.notification_service.listener;


import com.projekat.notification_service.dto.ActivationDto;
import com.projekat.notification_service.dto.UserDto;
import com.projekat.notification_service.listener.helper.MessageHelper;
import com.projekat.notification_service.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {
    private MessageHelper messageHelper;
    private NotificationService notificationService;

    public NotificationListener(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
    }

    @JmsListener(destination = "${destination.sendConfirmationEmail}", concurrency = "5-10")
    public void confirmationMail(Message message) throws JMSException, InterruptedException {
        ActivationDto activationDto = messageHelper.getMessage(message, ActivationDto.class);
        notificationService.sendActivationEmail(activationDto);
    }

    @JmsListener(destination = "${destination.sendPasswordEmail}", concurrency = "5-10")
    public void passwordResetMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
        notificationService.sendPasswordResetEmail(user);
    }

    @JmsListener(destination = "${destination.sendReservationEmail}", concurrency = "5-10")
    public void successfulReservationMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
//        notificationService.sendSuccessfulReservationNotification(user);
    }

    @JmsListener(destination = "${destination.sendCancellationEmail}", concurrency = "5-10")
    public void cancellationMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
//        notificationService.sendCancellationNotification(user);
    }

    @JmsListener(destination = "${destination.sendRemainderEmail}", concurrency = "5-10")
    public void remainderMail(Message message) throws JMSException {
        UserDto user = messageHelper.getMessage(message, UserDto.class);
//        notificationService.sendCancellationNotification(user);
    }
}
