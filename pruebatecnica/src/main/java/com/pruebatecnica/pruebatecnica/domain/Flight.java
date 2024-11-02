package com.pruebatecnica.pruebatecnica.domain;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String departureStation;

    @Column
    private String arrivalStation;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "idtransport", insertable = true, updatable = true) // Permitir que sea insertable y actualizable
    private Transport transport;

    @ManyToMany(mappedBy = "flights")
    @JsonIgnore
    private Set<Journey> journeys;

    public Flight() {}

    public Flight(int id, String departureStation, String arrivalStation, Double price, Transport transport, Set<Journey> journeys) {
        this.id = id;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.price = price;
        this.transport = transport;
        this.journeys = journeys;
    }

    // Getters y setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureStation() {
        return this.departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return this.arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Transport getTransport() {
        return this.transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Set<Journey> getJourneys() {
        return this.journeys;
    }

    public void setJourneys(Set<Journey> journeys) {
        this.journeys = journeys;
    }
}
