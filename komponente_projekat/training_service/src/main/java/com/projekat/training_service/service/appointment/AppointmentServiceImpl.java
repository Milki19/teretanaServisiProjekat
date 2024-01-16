package com.projekat.training_service.service.appointment;

import com.projekat.training_service.domain.Appointment;
import com.projekat.training_service.dto.AppointmentCreateDto;
import com.projekat.training_service.dto.AppointmentDto;
import com.projekat.training_service.mapper.AppointmentMapper;
import com.projekat.training_service.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDto> list = new ArrayList<>();

        for(Appointment a : appointments){
            list.add(appointmentMapper.appointmentToAppointmentDto(a));
        }
        return list;
    }
    @Override
    public AppointmentDto findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        return appointmentMapper.appointmentToAppointmentDto(appointment);
    }

    @Override
    public Optional<AppointmentDto> findByGymId(Long id) {
        LocalDate now = LocalDate.now();
        LocalDate fiveDaysLater = now.plusDays(5);

        return appointmentRepository.findAppointmentsByGymIdAndNextFiveDays(id, now, fiveDaysLater)
                .map(appointmentMapper::appointmentToAppointmentDto);
    }

    @Override
    public AppointmentDto add(AppointmentCreateDto appointmentCreateDto) {
        Appointment appointment = appointmentMapper.appointmentCreateDtoToAppointment(appointmentCreateDto);
        appointmentRepository.save(appointment);
        return appointmentMapper.appointmentToAppointmentDto(appointment);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}

