package com.projekat.training_service.service.trainings;

import com.projekat.training_service.dto.TrainingCreateDto;
import com.projekat.training_service.dto.TrainingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrainingService {

    Page<TrainingDto> findAll(Pageable pageable);
    TrainingDto add(TrainingCreateDto gymTrainingCreateDto);
    void deleteById(Long id);
}
