package com.projekat.notification_service.ms_sm_ac.mapper;

import com.projekat.notification_service.ms_sm_ac.domain.Manager;
import com.projekat.notification_service.ms_sm_ac.dto.ManagerCreateDto;
import com.projekat.notification_service.ms_sm_ac.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    public ManagerMapper() {
    }

    public ManagerDto managerToManagerDto(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setBirthDate(manager.getBirthDate());
        managerDto.setEmail(manager.getEmail());
        managerDto.setPassword(manager.getPassword());
        if(!manager.getUserType().toString().equals("MANAGER")){
            return null;
        }
        managerDto.setUserType(manager.getUserType());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) throws Exception {
        Manager manager = new Manager();
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setBirthDate(managerCreateDto.getBirthDate());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setPassword(managerCreateDto.getPassword());
        if(!managerCreateDto.getUserType().toString().equals("MANAGER")){
            throw new Exception("You can't add someone if they are not a manager.");
        }
        manager.setUserType(managerCreateDto.getUserType());
        return manager;
    }

}
