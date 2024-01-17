package com.projekat.training_service.mapper;

import com.projekat.training_service.domain.TrainingType;
import com.projekat.training_service.dto.TrainingTypeCreateDto;
import com.projekat.training_service.dto.TrainingTypeDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeMapper {

    public TrainingTypeDto trainingTypeToTrainingTypeDto(TrainingType trainingType){
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
        trainingTypeDto.setId(trainingType.getId());
        trainingTypeDto.setType(trainingType.getType());
        trainingTypeDto.setName(trainingType.getName());
        return trainingTypeDto;
    }

    public TrainingType trainingTypeCreateDtoToTrainingType(TrainingTypeCreateDto trainingTypeCreateDto){
        TrainingType trainingType = new TrainingType();
        trainingType.setName(trainingTypeCreateDto.getName());
        trainingType.setType(trainingTypeCreateDto.getType());
        trainingType.setName(trainingTypeCreateDto.getName());
        return trainingType;
    }
}
