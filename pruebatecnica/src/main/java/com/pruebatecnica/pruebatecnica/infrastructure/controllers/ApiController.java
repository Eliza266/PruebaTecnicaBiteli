package com.pruebatecnica.pruebatecnica.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.pruebatecnica.infrastructure.repository.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/guardarDatos")
    public ResponseEntity<String> guardarDatos() {
        apiService.fetchDataAndSave();
        return ResponseEntity.ok("Datos guardados correctamente");
    }
}
