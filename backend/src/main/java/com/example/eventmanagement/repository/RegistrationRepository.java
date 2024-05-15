package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}