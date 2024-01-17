package com.projekat.training_service.scheduler;

import com.projekat.training_service.repository.AppointmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleNotification {
    private AppointmentRepository appointmentRepository;

    public ScheduleNotification(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void executeHourlyTask(){
        //u rep query koji dohvata sve app kod kojih je razlika izmedju startTIme i trenutnog manja od 24h i trening je grupni i ima manje od 3 prijavljenih
        //mozda da ima polje da l je notif poslata
//        List<Appointment> appointmentList = appointmentRepository.findAppointments(trenutnovreme);
        //kad nadjemo svima posaljemo notif da je trening otkazan
    }
}