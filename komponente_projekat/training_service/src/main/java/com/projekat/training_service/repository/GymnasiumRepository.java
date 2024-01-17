package com.projekat.training_service.repository;

import com.projekat.training_service.domain.Gymnasium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymnasiumRepository extends JpaRepository<Gymnasium, Long> {
    Gymnasium findGymnasiumByName(String name);

}
