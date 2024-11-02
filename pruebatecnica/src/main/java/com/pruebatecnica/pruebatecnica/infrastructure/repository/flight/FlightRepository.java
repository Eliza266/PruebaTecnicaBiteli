package com.pruebatecnica.pruebatecnica.infrastructure.repository.flight;

import org.springframework.stereotype.Repository;

import com.pruebatecnica.pruebatecnica.domain.Flight;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer>{
    Optional<Flight> findByDepartureStationAndArrivalStation(String departureStation, String arrivalStation);

}
