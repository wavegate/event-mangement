package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Registration;
import com.example.eventmanagement.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    public Optional<Registration> findById(Long id) {
        return registrationRepository.findById(id);
    }

    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    public void deleteById(Long id) {
        registrationRepository.deleteById(id);
    }
}