package com.pruebatecnica.pruebatecnica.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "journeyflights")
public class JourneyFlight {

    @EmbeddedId
    private JourneyFlightPK id;

    @ManyToOne

    @JoinColumn(name = "idJourney", insertable = false, updatable = false)
    private Journey journey;

    @ManyToOne

    @JoinColumn(name = "idFlight", insertable = false, updatable = false)
    private Flight flight;

    public JourneyFlight() {
    }

    public JourneyFlight(JourneyFlightPK id, Journey journey, Flight flight) {
        this.id = id;
        this.journey = journey;
        this.flight = flight;
    }

    public JourneyFlightPK getId() {
        return this.id;
    }

    public void setId(JourneyFlightPK id) {
        this.id = id;
    }

    public Journey getJourney() {
        return this.journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
