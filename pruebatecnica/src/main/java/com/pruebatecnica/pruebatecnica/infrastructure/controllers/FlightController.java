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

import com.pruebatecnica.pruebatecnica.application.IFlight;
import com.pruebatecnica.pruebatecnica.domain.Flight;


@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private IFlight service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Flight flight) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(flight));
    }

    @GetMapping("/search/{destination}/{origin}")
    public Optional<Flight> searchFlight(@PathVariable String destination, @PathVariable String origin) {
        return service.findByDestinationAndOrigin(destination, origin);
    }

    
}
