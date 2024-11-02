package com.pruebatecnica.pruebatecnica.infrastructure.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.pruebatecnica.application.IJourney;
import com.pruebatecnica.pruebatecnica.domain.Flight;
import com.pruebatecnica.pruebatecnica.domain.Journey;
import com.pruebatecnica.pruebatecnica.domain.Transport;
import com.pruebatecnica.pruebatecnica.domain.dtos.FlightDto;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.flight.FlightRepository;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.journey.JourneyRepository;
import com.pruebatecnica.pruebatecnica.infrastructure.repository.transport.TransportRepository;

@Service
public class ApiService {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IJourney iJourney;

    public Optional<Journey> fetchDataAndSave(String arrivalStation, String departureStation) {
        Optional<Journey> journey = iJourney.findByArrivalStationAndDepartureStation(arrivalStation, departureStation);

        if (journey.isPresent()) {
            return journey;
        } else {
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
                        .filter(flight -> flight.getDepartureStation().equalsIgnoreCase(departureStation))
                        .filter(flight -> flight.getArrivalStation().equalsIgnoreCase(arrivalStation))
                        .collect(Collectors.toList());

                if (!directFlights.isEmpty()) {
                    System.out.println("Direct flights found: " + directFlights);
                    FlightDto directFlight = directFlights.get(0);
                    Journey savedJourney = saveFlightDetails(List.of(directFlight), departureStation, arrivalStation);
                    return Optional.of(savedJourney);
                } else {
                    // Buscar vuelos de conexión
                    List<FlightDto> connectingFlights = findConnectingFlights(flightDtoList, departureStation,
                            arrivalStation);
                    System.out.println("Connecting flights: " + connectingFlights);

                    if (!connectingFlights.isEmpty()) {
                        System.out.println("Connecting flights found: " + connectingFlights);
                        Journey savedJourney = saveFlightDetails(connectingFlights, departureStation, arrivalStation);
                        return Optional.of(savedJourney);
                    } else {
                        System.out.println("No route available for the specified journey.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private Journey saveFlightDetails(List<FlightDto> flightDtoList, String departureStation, String arrivalStation) {
        Journey journey;

        // Verificar si ya existe un Journey entre estas estaciones
        Optional<Journey> optionalJourney = journeyRepository.findByArrivalStationAndDepartureStation(arrivalStation,
                departureStation);
        if (optionalJourney.isPresent()) {
            journey = optionalJourney.get();
            System.out.println("Existing journey found: " + journey.getId());
        } else {
            // Si no existe, crear uno nuevo
            journey = new Journey();
            journey.setDeparturestation(departureStation);
            journey.setArrivalStation(arrivalStation);
            journey.setFlights(new HashSet<>());
        }

        // Procesar cada vuelo en la lista de vuelos de conexión
        for (FlightDto flightDto : flightDtoList) {
            // Buscar transporte existente o crear uno nuevo
            Optional<Transport> optionalTransport = transportRepository.findByFlightCarrierAndFlightNumber(
                    flightDto.getFlightCarrier(), flightDto.getFlightNumber());

            Transport transport;
            if (optionalTransport.isPresent()) {
                transport = optionalTransport.get();
                System.out.println("Existing transport found: " + transport.getId());
            } else {
                transport = new Transport();
                transport.setFlightCarrier(flightDto.getFlightCarrier());
                transport.setFlightNumber(flightDto.getFlightNumber());
                transport = transportRepository.save(transport);
                System.out.println("New transport saved: " + transport.getId());
            }

            // Buscar vuelo existente o crear uno nuevo
            Optional<Flight> optionalFlight = flightRepository.findByArrivalStationAndDepartureStation(
                    flightDto.getArrivalStation(), flightDto.getDepartureStation());

            Flight flight;
            if (optionalFlight.isPresent()) {
                flight = optionalFlight.get();
                System.out.println("Existing flight found: " + flight.getId());
            } else {
                flight = new Flight();
                flight.setDepartureStation(flightDto.getDepartureStation());
                flight.setArrivalStation(flightDto.getArrivalStation());
                flight.setPrice(flightDto.getPrice());
                flight.setTransport(transport);
                flight = flightRepository.save(flight);
                System.out.println("New flight saved: " + flight.getId());
            }

            // Añadir el vuelo al Journey si no está ya en la lista
            if (!journey.getFlights().contains(flight)) {
                journey.getFlights().add(flight);
                System.out.println("Flight added to journey: " + flight);
            } else {
                System.out.println("Flight already exists in journey, not adding again.");
            }
        }

        journey = journeyRepository.save(journey); // Guardar el Journey con todos los vuelos de conexión
        return journey;
    }

    private List<FlightDto> findConnectingFlights(List<FlightDto> flightDtoList, String origin, String destination) {
        List<FlightDto> connectingFlights = new ArrayList<>();

        // Filtra los vuelos que salen del origen
        List<FlightDto> departureFlights = flightDtoList.stream()
                .filter(flight -> flight.getDepartureStation().equalsIgnoreCase(origin))
                .collect(Collectors.toList());

        // Busca vuelos de conexión
        for (FlightDto departureFlight : departureFlights) {
            String intermediateDestination = departureFlight.getArrivalStation();

            // Busca vuelos que salgan del destino intermedio y lleguen al destino final
            List<FlightDto> flightsToDestination = flightDtoList.stream()
                    .filter(flight -> flight.getDepartureStation().equalsIgnoreCase(intermediateDestination)
                            && flight.getArrivalStation().equalsIgnoreCase(destination))
                    .collect(Collectors.toList());

            if (!flightsToDestination.isEmpty()) {
                connectingFlights.add(departureFlight);
                connectingFlights.add(flightsToDestination.get(0));
                break;
            }
        }
        return connectingFlights;
    }
}
