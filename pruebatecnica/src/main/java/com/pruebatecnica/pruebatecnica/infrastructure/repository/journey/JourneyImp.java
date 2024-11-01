package com.pruebatecnica.pruebatecnica.infrastructure.repository.journey;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.pruebatecnica.application.IJourney;
import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class JourneyImp implements IJourney {

    @Autowired
    private JourneyRepository journeyRepository;

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Optional<Journey> findByDestinationAndOrigin(String destination, String origin) {

        return Optional.ofNullable(journeyRepository.findByDestinationAndOrigin(destination, origin)
                .orElseThrow(() -> new GlobalExceptions(
                        "La búsqueda no devolvió resultados para el destino y origen proporcionados")));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Journey save(Journey journey) {
        if (journey == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return journeyRepository.save(journey);
    }

}
