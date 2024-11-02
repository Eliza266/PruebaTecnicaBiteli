package com.pruebatecnica.pruebatecnica.application;

import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Flight;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface IFlight {

    // Optional<Flight> findByArrivalStationAndDepartureStation(String arrivalStation, String departureStation) throws GlobalExceptions;
    Optional<Flight> findByArrivalStationAndDepartureStation(String arrivalStation, String departureStation);


    Flight save(Flight flight) throws GlobalExceptions;
}
