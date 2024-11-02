package com.pruebatecnica.pruebatecnica.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "journeys")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String arrivalStation;

    @Column
    private String departureStation;

    @ManyToMany
    @JoinTable(
        name = "journey_flight",
        joinColumns = @JoinColumn(name = "journey_id"),
        inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    private Set<Flight> flights = new HashSet<>(); 

    public Journey() {}

    public Journey(int id, String arrivalStation, String departurestation, Set<Flight> flights) {
        this.id = id;
        this.arrivalStation = arrivalStation;
        this.departureStation = departurestation;
        this.flights = flights;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrivalStation() {
        return this.arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDeparturestation() {
        return this.departureStation;
    }

    public void setDeparturestation(String departurestation) {
        this.departureStation = departurestation;
    }

    public Set<Flight> getFlights() {
        return this.flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    // Método para calcular el precio total del Journey sumando los precios de los vuelos
    private Double calculatePrice() {
        return flights.stream()
                      .mapToDouble(Flight::getPrice)
                      .sum();
    }

    // Método getter para price que llama a calculatePrice()
    public Double getPrice() {
        return calculatePrice();
    }
}
