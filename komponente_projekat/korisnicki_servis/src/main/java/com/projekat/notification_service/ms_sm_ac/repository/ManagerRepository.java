package com.projekat.notification_service.ms_sm_ac.repository;

import com.projekat.notification_service.ms_sm_ac.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findClientByUsernameAndPassword(String username, String password);

}
