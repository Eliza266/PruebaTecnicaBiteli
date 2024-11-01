package com.pruebatecnica.pruebatecnica.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class JourneyFlightPK implements Serializable {

    @Column(name = "idJourney")
    private int idJourney;

    @Column(name = "idFlight")
    private int idFlight;


    public JourneyFlightPK() {
    }

    public JourneyFlightPK(int idJourney, int idFlight) {
        this.idJourney = idJourney;
        this.idFlight = idFlight;
    }

    public int getIdJourney() {
        return this.idJourney;
    }

    public void setIdJourney(int idJourney) {
        this.idJourney = idJourney;
    }

    public int getIdFlight() {
        return this.idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    
}
