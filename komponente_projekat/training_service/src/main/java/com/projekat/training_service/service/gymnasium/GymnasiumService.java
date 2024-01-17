package com.projekat.training_service.service.gymnasium;

import com.projekat.training_service.dto.GymnasiumCreateDto;
import com.projekat.training_service.dto.GymnasiumDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GymnasiumService {
    Page<GymnasiumDto> findAll(Pageable pageable);
    GymnasiumDto findById(Long id);
    GymnasiumDto add(GymnasiumCreateDto gymCreateDto);
    GymnasiumDto findGymnasiumByName(String name);
    void deleteById(Long id);
}
