package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.Registration;
import com.example.eventmanagement.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable Long id) {
        Optional<Registration> registration = registrationService.findById(id);
        return registration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Registration createRegistration(@RequestBody Registration registration) {
        return registrationService.save(registration);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id,
            @RequestBody Registration registrationDetails) {
        Optional<Registration> registration = registrationService.findById(id);
        if (registration.isPresent()) {
            Registration updatedRegistration = registration.get();
            updatedRegistration.setUser(registrationDetails.getUser());
            updatedRegistration.setEvent(registrationDetails.getEvent());
            return ResponseEntity.ok(registrationService.save(updatedRegistration));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}