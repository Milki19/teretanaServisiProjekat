package com.projekat.training_service.repository;

import com.projekat.training_service.domain.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
}
