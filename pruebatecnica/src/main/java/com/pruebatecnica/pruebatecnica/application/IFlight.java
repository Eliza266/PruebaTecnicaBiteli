package com.pruebatecnica.pruebatecnica.application;

import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Flight;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface IFlight {

    Optional<Flight> findByDestinationAndOrigin(String destination, String origin) throws GlobalExceptions;

    Flight save(Flight flight) throws GlobalExceptions;
}
