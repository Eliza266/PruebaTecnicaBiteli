package com.pruebatecnica.pruebatecnica.infrastructure.repository.journey;

import org.springframework.stereotype.Repository;

import com.pruebatecnica.pruebatecnica.domain.Journey;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Integer>{

    Optional<Journey> findByDepartureStationAndArrivalStation(String departureStation, String arrivalStation);
}
