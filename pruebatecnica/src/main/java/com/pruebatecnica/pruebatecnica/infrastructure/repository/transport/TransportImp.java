package com.pruebatecnica.pruebatecnica.infrastructure.repository.transport;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.pruebatecnica.application.ITransport;
import com.pruebatecnica.pruebatecnica.domain.Transport;
import com.pruebatecnica.pruebatecnica.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class TransportImp implements ITransport {

    @Autowired
    private TransportRepository transportRepository;

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Optional<Transport> findByDestinationAndOrigin(String destination, String origin) {

        return Optional.ofNullable(transportRepository.findByDestinationAndOrigin(destination, origin)
                .orElseThrow(() -> new GlobalExceptions(
                        "La búsqueda no devolvió resultados para el destino y origen proporcionados")));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Transport save(Transport transport) {
        if (transport == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return transportRepository.save(transport);
    }

}
