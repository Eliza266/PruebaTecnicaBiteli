package com.pruebatecnica.pruebatecnica.application;

import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Transport;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITransport {
    
    Optional<Transport> findByFlightCarrierAndFlightNumber(String flightCarrier, String flightNumber);


    Transport save(Transport transport) throws GlobalExceptions;

}
