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

import com.pruebatecnica.pruebatecnica.application.ITransport;
import com.pruebatecnica.pruebatecnica.domain.Transport;

@RestController
@RequestMapping("/api/transports")
public class TransportController {
    @Autowired
    private ITransport service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Transport transport) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transport));
    }

    @GetMapping("/search/{carrier}/{number}")
    public Optional<Transport> searchTransport(@PathVariable String carrier, @PathVariable String number) {
        return service.findByFlightCarrierAndFlightNumber(carrier, number);
    }

}
