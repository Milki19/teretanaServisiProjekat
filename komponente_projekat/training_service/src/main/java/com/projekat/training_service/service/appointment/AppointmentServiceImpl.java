package com.projekat.training_service.service.appointment;

import com.projekat.training_service.domain.Appointment;
import com.projekat.training_service.domain.Reservation;
import com.projekat.training_service.dto.AppointmentCreateDto;
import com.projekat.training_service.dto.AppointmentDto;
import com.projekat.training_service.dto.CancellationDto;
import com.projekat.training_service.listener.helper.MessageHelper;
import com.projekat.training_service.mapper.AppointmentMapper;
import com.projekat.training_service.repository.AppointmentRepository;
import com.projekat.training_service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private ReservationRepository reservationRepository;
    private AppointmentMapper appointmentMapper;
    private JmsTemplate jmsTemplate;
    private String appointmentCancelQueueDestination;
    private MessageHelper messageHelper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ReservationRepository reservationRepository, AppointmentMapper appointmentMapper,
                                  JmsTemplate jmsTemplate, @Value("${destination.appointmentCancel}") String appointmentCancelQueueDestination, MessageHelper messageHelper) {
        this.appointmentRepository = appointmentRepository;
        this.reservationRepository = reservationRepository;
        this.appointmentMapper = appointmentMapper;
        this.jmsTemplate = jmsTemplate;
        this.appointmentCancelQueueDestination = appointmentCancelQueueDestination;
        this.messageHelper = messageHelper;
    }

    @Override
    public List<AppointmentDto> findAll() {
        LocalDate now = LocalDate.now();
        LocalDate fiveDaysLater = now.plusDays(5);

        List<Appointment> list = appointmentRepository.findAppointmentsByDateBefore(fiveDaysLater);
        List<AppointmentDto> res = new ArrayList<>();

        for(Appointment a: list){
            res.add(appointmentMapper.appointmentToAppointmentDto(a));
        }

        return res;
    }
    @Override
    public AppointmentDto findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        return appointmentMapper.appointmentToAppointmentDto(appointment);
    }

    @Override
    public List<AppointmentDto> findByGymId(Long id) {
        List<Appointment> list = appointmentRepository.findAppointmentsByGymTrainingGymId(id);
        List<AppointmentDto> res = new ArrayList<>();

        for(Appointment a: list){
            res.add(appointmentMapper.appointmentToAppointmentDto(a));
        }

        return res;
    }

    @Override
    public AppointmentDto add(AppointmentCreateDto appointmentCreateDto) {
        Appointment appointment = appointmentMapper.appointmentCreateDtoToAppointment(appointmentCreateDto);
        appointmentRepository.save(appointment);
        return appointmentMapper.appointmentToAppointmentDto(appointment);
    }

    @Override
    public void deleteById(Long id) {
        List<Reservation> reservations = reservationRepository.findByAppointmentId(id);
        List<Long> userIds = new ArrayList<>();

        for(Reservation r: reservations){
            userIds.add(r.getUserId());
            reservationRepository.delete(r);
        }

        CancellationDto cancellationDto  = new CancellationDto(userIds, appointmentRepository.findById(id).get().getTraining().getGym().getName());
        jmsTemplate.convertAndSend(appointmentCancelQueueDestination, messageHelper.createTextMessage(cancellationDto));

        appointmentRepository.deleteById(id);
    }
}

