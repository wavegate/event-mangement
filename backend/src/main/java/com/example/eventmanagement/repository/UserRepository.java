package com.example.eventmanagement.repository;

import com.example.eventmanagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}