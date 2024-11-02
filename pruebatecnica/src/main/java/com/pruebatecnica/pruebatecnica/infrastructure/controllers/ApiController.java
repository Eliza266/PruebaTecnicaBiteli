package com.pruebatecnica.pruebatecnica.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.ApiService;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Transactional(rollbackFor = GlobalExceptions.class)
    @GetMapping("/find/{departureStation}/{arrivalStation}")
    public ResponseEntity<Journey> findData(@PathVariable String departureStation,
            @PathVariable String arrivalStation) {

        Optional<Journey> journey = apiService.fetchDataAndSave(departureStation, arrivalStation);

        if (journey.isPresent()) {
            return ResponseEntity.ok(journey.get());
        } else {
            // Lanza la excepción personalizada si no se encuentra la ruta deseada
            throw new GlobalExceptions("No se pudo encontrar o guardar el Journey para las estaciones especificadas.");
        }
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> getStations() {
        List<String> stations = apiService.cities();
        return ResponseEntity.ok(stations);
    }
}
