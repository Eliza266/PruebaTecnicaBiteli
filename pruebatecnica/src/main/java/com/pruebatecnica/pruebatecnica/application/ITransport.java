package com.pruebatecnica.pruebatecnica.application;

import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Transport;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface ITransport {
    Transport save(Transport transport) throws GlobalExceptions;

    Optional<Transport> findByDestinationAndOrigin(String destination, String origin) throws GlobalExceptions;
}
