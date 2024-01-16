package com.projekat.training_service.service.trainingtype;

import com.projekat.training_service.dto.TrainingTypeCreateDto;
import com.projekat.training_service.dto.TrainingTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrainingTypeService {

    Page<TrainingTypeDto> findAll(Pageable pageable);
    TrainingTypeDto add(TrainingTypeCreateDto trainingTypeCreateDto);
    void deleteById(Long id);

}
