package com.pruebatecnica.pruebatecnica.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.pruebatecnica.domain.dtos.FlightDto;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    // @Autowired
    // private JourneyRepository journeyRepository;

    @SuppressWarnings("null")
    public void fetchDataAndSave() {
        String url = "https://bitecingcom.ipage.com/testapi/avanzado.js";

        String response = restTemplate.getForObject(url, String.class);

        response = response.trim();

        response = response.replaceAll(",\\s*}", "}");

        if (response.endsWith(",")) {
            response = response.substring(0, response.length() - 1);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FlightDto[] datos = objectMapper.readValue(response, FlightDto[].class);

            if (datos != null) {
                for (FlightDto dato : datos) {
                    System.out.println(dato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
