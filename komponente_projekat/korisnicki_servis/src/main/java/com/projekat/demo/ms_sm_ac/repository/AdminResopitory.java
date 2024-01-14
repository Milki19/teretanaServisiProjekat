package com.projekat.demo.ms_sm_ac.repository;

import com.projekat.demo.ms_sm_ac.domain.Admin;
import com.projekat.demo.ms_sm_ac.domain.Client;
import com.projekat.demo.ms_sm_ac.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminResopitory extends JpaRepository<Admin, Long> {

    Optional<Admin> findClientByUsernameAndPassword(String username, String password);

}
