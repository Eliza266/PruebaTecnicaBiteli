package com.pruebatecnica.pruebatecnica.infrastructure.repository.flight;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.pruebatecnica.application.IFlight;
import com.pruebatecnica.pruebatecnica.domain.Flight;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class FlightImp implements IFlight {

    @Autowired
    private FlightRepository flightRepository;

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Optional<Flight> findByDepartureStationAndArrivalStation(String departureStation, String arrivalStation) {

        // return
        // Optional.ofNullable(flightRepository.findByDepartureStationeAndDepartureStation(departureStatione,
        // departureStation)
        // .orElseThrow(() -> new GlobalExceptions(
        // "La búsqueda no devolvió resultados para el destino y origen
        // proporcionados")));

        Optional<Flight> flight = flightRepository.findByDepartureStationAndArrivalStation(departureStation,
                arrivalStation);
        return flight;
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Flight save(Flight flight) {
        if (flight == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return flightRepository.save(flight);
    }

}
