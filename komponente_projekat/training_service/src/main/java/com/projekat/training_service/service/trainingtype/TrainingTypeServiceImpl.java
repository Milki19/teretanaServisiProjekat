package com.projekat.training_service.service.trainingtype;

import com.projekat.training_service.domain.TrainingType;
import com.projekat.training_service.dto.TrainingTypeCreateDto;
import com.projekat.training_service.dto.TrainingTypeDto;
import com.projekat.training_service.mapper.TrainingTypeMapper;
import com.projekat.training_service.repository.TrainingTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {
    private TrainingTypeRepository trainingTypeRepository;
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    @Override
    public Page<TrainingTypeDto> findAll(Pageable pageable) {
        return trainingTypeRepository.findAll(pageable)
                .map(trainingTypeMapper::trainingTypeToTrainingTypeDto);
    }

    @Override
    public TrainingTypeDto add(TrainingTypeCreateDto trainingTypeCreateDto) {
        TrainingType trainingType = trainingTypeMapper.trainingTypeCreateDtoToTrainingType(trainingTypeCreateDto);
        trainingTypeRepository.save(trainingType);
        return trainingTypeMapper.trainingTypeToTrainingTypeDto(trainingType);
    }

    @Override
    public void deleteById(Long id) {
        trainingTypeRepository.deleteById(id);
    }
}
