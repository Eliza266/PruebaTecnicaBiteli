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
    public Transport save(Transport transport) {
        if (transport == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return transportRepository.save(transport);
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Optional<Transport> findByFlightCarrierAndFlightNumber(String flightCarrier, String flightNumber) {
        Optional<Transport> transport = transportRepository.findByFlightCarrierAndFlightNumber(flightCarrier,
                flightNumber);
        return transport;
    }

}
