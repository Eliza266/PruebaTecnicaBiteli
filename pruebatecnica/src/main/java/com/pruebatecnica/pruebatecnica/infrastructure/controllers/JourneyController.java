package com.pruebatecnica.pruebatecnica.infrastructure.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.pruebatecnica.application.IJourney;
import com.pruebatecnica.pruebatecnica.domain.Journey;

@RestController
@RequestMapping("/api/journeys")
public class JourneyController {
    @Autowired
    private IJourney service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Journey journey) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(journey));
    }

    @GetMapping("/search/{destination}/{origin}")
    public Optional<Journey> searchJourney(@PathVariable String arrivalStation, @PathVariable String departureStation) {
        return service.findByArrivalStationAndDepartureStation(arrivalStation, departureStation);
    }

    
}
