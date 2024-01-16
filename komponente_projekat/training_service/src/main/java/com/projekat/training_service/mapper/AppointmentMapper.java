package com.projekat.training_service.mapper;

import com.projekat.training_service.domain.Appointment;
import com.projekat.training_service.domain.Training;
import com.projekat.training_service.dto.AppointmentCreateDto;
import com.projekat.training_service.dto.AppointmentDto;
import com.projekat.training_service.exceptions.NotFoundException;
import com.projekat.training_service.repository.TrainingRepository;

public class AppointmentMapper {

    private TrainingRepository trainingRepository;
    private TrainingMapper trainingMapper;

    public AppointmentMapper(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public AppointmentDto appointmentToAppointmentDto(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setEndTime(appointment.getEndTime());
        appointmentDto.setGymTrainingDto(trainingMapper.trainingToTrainingDto(appointment.getTraining()));
        appointmentDto.setCapacity(trainingMapper.trainingToTrainingDto(appointment.getTraining()).getGymnasiumDto().getCapacity());
        return appointmentDto;
    }

    public Appointment appointmentCreateDtoToAppointment(AppointmentCreateDto appointmentCreateDto){
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentCreateDto.getDate());
        appointment.setStartTime(appointmentCreateDto.getStartTime());
        appointment.setEndTime(appointmentCreateDto.getEndTime());
        Training training = trainingRepository.findById(appointmentCreateDto.getGymTraining())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Gym with id: %d does not exists.", appointmentCreateDto.getGymTraining())));
        appointment.setTraining(training);
        appointment.setCapacity(training.getGym().getCapacity());
        return appointment;
    }
}
