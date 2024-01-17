package com.projekat.training_service.mapper;

import com.projekat.training_service.domain.Gymnasium;
import com.projekat.training_service.dto.GymnasiumCreateDto;
import com.projekat.training_service.dto.GymnasiumDto;
import org.springframework.stereotype.Component;

@Component
public class GymnasiumMapper {

    public GymnasiumDto gymToGymDto(Gymnasium gym){
        GymnasiumDto gymDto = new GymnasiumDto();
        gymDto.setId(gym.getId());
        gymDto.setName(gym.getName());
        gymDto.setDescription(gym.getDescription());
        gymDto.setNumberOfCoaches(gym.getNumberOfCoaches());
        gymDto.setCapacity(gym.getCapacity());
        return gymDto;
    }

    public Gymnasium gymCreateDtoToGym(GymnasiumCreateDto gymnasiumCreateDto){
        Gymnasium gym = new Gymnasium();
        gym.setName(gymnasiumCreateDto.getName());
        gym.setDescription(gymnasiumCreateDto.getDescription());
        gym.setNumberOfCoaches(gymnasiumCreateDto.getNumberOfCoaches());
        gym.setCapacity(gymnasiumCreateDto.getCapacity());
        return gym;
    }

}
