package com.pruebatecnica.pruebatecnica.application;


import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface IJourney {

    // Optional<Journey> findByDepartureStationeAndDepartureStation(String departureStatione, String departureStation) throws GlobalExceptions;
    Optional<Journey> findByDepartureStationAndarrivalStation(String departureStation, String arrivalStation);


    Journey save(Journey journey) throws GlobalExceptions;

}
