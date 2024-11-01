package com.pruebatecnica.pruebatecnica.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "journeys")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String destination;

    @Column
    private String origin;

    @Column
    private Double price;

    @ManyToMany
    @JoinTable(
        name = "journey_flight",
        joinColumns = @JoinColumn(name = "journey_id"),
        inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    private Set<Flight> flights;

    public Journey() {}

    public Journey(int id, String destination, String origin, Double price) {
        this.id = id;
        this.destination = destination;
        this.origin = origin;
        this.price = price;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Set<Flight> getFlights() { return flights; }
    public void setFlights(Set<Flight> flights) { this.flights = flights; }
}
