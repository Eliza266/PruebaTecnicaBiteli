package com.pruebatecnica.pruebatecnica.infrastructure.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/find/{departureStation}/{arrivalStation}")
public ResponseEntity<Journey> findData(@PathVariable String departureStation, @PathVariable String arrivalStation) {
    Optional<Journey> journey = apiService.fetchDataAndSave(departureStation, arrivalStation);
    
    if (journey.isPresent()) {
        return ResponseEntity.ok(journey.get()); // Devuelve el objeto Journey
    } else {
        return ResponseEntity.status(404).body(null); // Devuelve un 404 si no se encuentra
    }
}
}
