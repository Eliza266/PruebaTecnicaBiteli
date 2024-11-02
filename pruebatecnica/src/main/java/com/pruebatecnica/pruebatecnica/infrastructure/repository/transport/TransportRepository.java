package com.pruebatecnica.pruebatecnica.infrastructure.repository.transport;

import org.springframework.stereotype.Repository;
import com.pruebatecnica.pruebatecnica.domain.Transport;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface TransportRepository extends CrudRepository<Transport, Integer>{
        Optional<Transport> findByFlightCarrierAndFlightNumber(String flightCarrier, String flightNumber);

}
