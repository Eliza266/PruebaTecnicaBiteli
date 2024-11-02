package com.pruebatecnica.pruebatecnica.application;


import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface IJourney {

    // Optional<Journey> findByArrivalStationAndDepartureStation(String arrivalStation, String departureStation) throws GlobalExceptions;
    Optional<Journey> findByArrivalStationAndDepartureStation(String arrivalStation, String departureStation);


    Journey save(Journey journey) throws GlobalExceptions;

}
