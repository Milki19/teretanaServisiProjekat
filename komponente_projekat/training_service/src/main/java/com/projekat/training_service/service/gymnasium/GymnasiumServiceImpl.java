package com.projekat.training_service.service.gymnasium;

import com.projekat.training_service.domain.Gymnasium;
import com.projekat.training_service.dto.GymnasiumCreateDto;
import com.projekat.training_service.dto.GymnasiumDto;
import com.projekat.training_service.mapper.GymnasiumMapper;
import com.projekat.training_service.repository.GymnasiumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GymnasiumServiceImpl implements GymnasiumService{

    private GymnasiumRepository gymnasiumRepository;
    private GymnasiumMapper gymnasiumMapper;

    public GymnasiumServiceImpl(GymnasiumRepository gymnasiumRepository, GymnasiumMapper gymnasiumMapper) {
        this.gymnasiumRepository = gymnasiumRepository;
        this.gymnasiumMapper = gymnasiumMapper;
    }

    @Override
    public Page<GymnasiumDto> findAll(Pageable pageable) {
        return gymnasiumRepository.findAll(pageable)
                .map(gymnasiumMapper::gymToGymDto);
    }

    @Override
    public GymnasiumDto findById(Long id) {
        Gymnasium gym = gymnasiumRepository.findById(id).get();
        return gymnasiumMapper.gymToGymDto(gym);
    }

    @Override
    public GymnasiumDto add(GymnasiumCreateDto gymCreateDto) {
        Gymnasium gym = gymnasiumMapper.gymCreateDtoToGym(gymCreateDto);
        gymnasiumRepository.save(gym);
        return gymnasiumMapper.gymToGymDto(gym);
    }

    @Override
    public void deleteById(Long id) {
        gymnasiumRepository.deleteById(id);
    }

}
