package com.projekat.notification_service.ms_sm_ac.repository;

import com.projekat.notification_service.ms_sm_ac.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminResopitory extends JpaRepository<Admin, Long> {

    Optional<Admin> findClientByUsernameAndPassword(String username, String password);

}
