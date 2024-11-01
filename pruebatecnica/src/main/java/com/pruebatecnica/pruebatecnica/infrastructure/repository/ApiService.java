package com.pruebatecnica.pruebatecnica.infrastructure.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.pruebatecnica.application.IFlight;
import com.pruebatecnica.pruebatecnica.application.IJourney;
import com.pruebatecnica.pruebatecnica.application.ITransport;
import com.pruebatecnica.pruebatecnica.domain.Flight;
import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.domain.Transport;
import com.pruebatecnica.pruebatecnica.domain.dtos.FlightDto;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.flight.FlightRepository;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.journey.JourneyRepository;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.transport.TransportRepository;

@Service
public class ApiService {
    private TransportRepository transportRepository;
    private FlightRepository flightRepository;
    private JourneyRepository journeyRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IJourney iJourney;

    public Optional<Journey> fetchDataAndSave(String origen, String destino) {
        Optional<Journey> journey = iJourney.findByDestinationAndOrigin(destino, origen);

        if (journey.isPresent()) {
            return journey;
        } else {
            // Obtener datos de la API
            String url = "https://bitecingcom.ipage.com/testapi/avanzado.js";
            String response = restTemplate.getForObject(url, String.class);
            response = response.replaceAll(",\\s*}", "}").trim();
            if (response.endsWith(",")) {
                response = response.substring(0, response.length() - 1);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                FlightDto[] datos = objectMapper.readValue(response, FlightDto[].class);
                List<FlightDto> flightDtoList = Arrays.asList(datos);

                // Intentar encontrar un vuelo directo
                List<FlightDto> directFlights = flightDtoList.stream()
                        .filter(flight -> flight.getDepartureStation().equalsIgnoreCase(origen))
                        .filter(flight -> flight.getArrivalStation().equalsIgnoreCase(destino))
                        .collect(Collectors.toList());

                if (!directFlights.isEmpty()) {
                    FlightDto directFlight = directFlights.get(0);
                    Journey savedJourney = saveFlightDetails(directFlight, origen, destino);
                    System.out.println("Vuelo directo encontrado y guardado: " + directFlight);
                    return Optional.of(savedJourney);
                } else {
                    // Buscar vuelos de conexión
                    List<FlightDto> connectingFlights = findConnectingFlights(flightDtoList, origen, destino);
                    if (!connectingFlights.isEmpty()) {
                        for (FlightDto connectingFlight : connectingFlights) {
                            saveFlightDetails(connectingFlight, origen, destino);
                        }
                    } else {
                        System.out.println("No se puede hacer esa ruta.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private Journey saveFlightDetails(FlightDto flightDto, String origen, String destino) {
        // Guardar datos de transport
        Transport transport = new Transport();
        transport.setFlightCarrier(flightDto.getFlightCarrier());
        transport.setFlightNumber(flightDto.getFlightNumber());
        transport = transportRepository.save(transport);

        // Guardar datos de flight
        Flight flight = new Flight();
        flight.setDepartureStation(flightDto.getDepartureStation());
        flight.setArrivalStation(flightDto.getArrivalStation());
        flight.setPrice(flightDto.getPrice());
        flight.setTransport(transport);
        flight = flightRepository.save(flight);

        // Guardar datos de journey
        Journey journey = new Journey();
        journey.setOrigin(origen);
        journey.setDestination(destino);
        journey.setPrice(flightDto.getPrice());
        journey = journeyRepository.save(journey);

        return journey;
    }

    private List<FlightDto> findConnectingFlights(List<FlightDto> flightDtoList, String origen, String destino) {
        List<FlightDto> connectingFlights = new ArrayList<>();

        // Filtra los vuelos que salen del origen
        List<FlightDto> departureFlights = flightDtoList.stream()
                .filter(flight -> flight.getDepartureStation().equalsIgnoreCase(origen))
                .collect(Collectors.toList());

        // Busca vuelos de conexión
        for (FlightDto departureFlight : departureFlights) {
            String intermediateDestination = departureFlight.getArrivalStation();

            // Busca vuelos que salgan del destino intermedio y lleguen al destino final
            List<FlightDto> flightsToDestination = flightDtoList.stream()
                    .filter(flight -> flight.getDepartureStation().equalsIgnoreCase(intermediateDestination)
                            && flight.getArrivalStation().equalsIgnoreCase(destino))
                    .collect(Collectors.toList());

            if (!flightsToDestination.isEmpty()) {
                connectingFlights.add(departureFlight);
                connectingFlights.add(flightsToDestination.get(0));
                break; // Encontramos una conexión válida, salir del bucle
            }
        }
        return connectingFlights;
    }
}
