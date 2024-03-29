package com.projekat.training_service.mapper;

import com.projekat.training_service.domain.Training;
import com.projekat.training_service.dto.TrainingCreateDto;
import com.projekat.training_service.dto.TrainingDto;
import com.projekat.training_service.exceptions.NotFoundException;
import com.projekat.training_service.repository.GymnasiumRepository;
import com.projekat.training_service.repository.TrainingTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    private GymnasiumRepository gymRepository;
    private TrainingTypeRepository trainingTypeRepository;
    private GymnasiumMapper gymMapper;
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingMapper(GymnasiumRepository gymRepository, TrainingTypeRepository trainingTypeRepository,
                             GymnasiumMapper gymMapper, TrainingTypeMapper trainingTypeMapper) {
        this.gymRepository = gymRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.gymMapper = gymMapper;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    public TrainingDto trainingToTrainingDto(Training training){
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setGymnasiumDto(gymMapper.gymToGymDto(training.getGym()));
        trainingDto.setTrainingTypeDto(trainingTypeMapper.trainingTypeToTrainingTypeDto(training.getTrainingType()));
        trainingDto.setPrice(training.getPrice());
        return trainingDto;
    }

    public Training trainingCreateDtoToTraining(TrainingCreateDto trainingCreateDto){
        Training training = new Training();
        training.setGym(gymRepository.findById(trainingCreateDto.getGym())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Gym with id: %d does not exists.", trainingCreateDto.getGym()))));
        training.setTrainingType(trainingTypeRepository.findById(trainingCreateDto.getTrainingType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("TrainingType with id: %d does not exists.", trainingCreateDto.getTrainingType()))));
        training.setPrice(trainingCreateDto.getPrice());
        return training;
    }


}
