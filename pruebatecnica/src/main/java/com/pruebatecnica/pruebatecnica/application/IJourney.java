package com.pruebatecnica.pruebatecnica.application;


import java.util.Optional;

import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

public interface IJourney {

    Optional<Journey> findByDestinationAndOrigin(String destination, String origin) throws GlobalExceptions;

    Journey save(Journey journey) throws GlobalExceptions;

}
