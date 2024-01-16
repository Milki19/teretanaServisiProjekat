package com.projekat.training_service.service.trainings;

import com.projekat.training_service.domain.Training;
import com.projekat.training_service.dto.TrainingCreateDto;
import com.projekat.training_service.dto.TrainingDto;
import com.projekat.training_service.mapper.TrainingMapper;
import com.projekat.training_service.repository.TrainingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TrainingServiceImpl implements TrainingService{

    private TrainingRepository trainingRepository;
    private TrainingMapper gymTrainingMapper;

    public TrainingServiceImpl(TrainingRepository gymTrainingRepository, TrainingMapper gymTrainingMapper) {
        this.trainingRepository = gymTrainingRepository;
        this.gymTrainingMapper = gymTrainingMapper;
    }

    @Override
    public Page<TrainingDto> findAll(Pageable pageable) {
        return trainingRepository.findAll(pageable)
                .map(gymTrainingMapper::trainingToTrainingDto);
    }

    @Override
    public TrainingDto add(TrainingCreateDto gymTrainingCreateDto) {
        Training training = gymTrainingMapper.trainingCreateDtoToTraining(gymTrainingCreateDto);
        trainingRepository.save(training);
        return gymTrainingMapper.trainingToTrainingDto(training);
    }

    @Override
    public void deleteById(Long id) {
        trainingRepository.deleteById(id);
    }
}
