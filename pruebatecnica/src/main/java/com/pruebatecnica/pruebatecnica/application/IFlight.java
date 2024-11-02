package com.pruebatecnica.pruebatecnica.application;

import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Flight;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface IFlight {

    // Optional<Flight> findByDepartureStationeAndDepartureStation(String
    // departureStatione, String departureStation) throws GlobalExceptions;
    Optional<Flight> findByDepartureStationAndArrivalStation(String departureStatione, String arrivalStation);

    Flight save(Flight flight) throws GlobalExceptions;
}
