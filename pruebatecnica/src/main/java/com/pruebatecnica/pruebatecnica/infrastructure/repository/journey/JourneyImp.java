package com.pruebatecnica.pruebatecnica.infrastructure.repository.journey;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.pruebatecnica.application.IJourney;
import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class JourneyImp implements IJourney {

    @Autowired
    private JourneyRepository journeyRepository;

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Optional<Journey> findByArrivalStationAndDepartureStation(String arrivalStation, String departureStation) {

        // return Optional
        //         .ofNullable(journeyRepository.findByArrivalStationAndDepartureStation(arrivalStation, departureStation)
        //                 .orElseThrow(() -> new GlobalExceptions(
        //                         "La búsqueda no devolvió resultados para el destino y origen proporcionados")));
        Optional<Journey> journey = journeyRepository.findByArrivalStationAndDepartureStation(arrivalStation, departureStation);

        return journey;
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Journey save(Journey journey) {
        if (journey == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return journeyRepository.save(journey);
    }

}
