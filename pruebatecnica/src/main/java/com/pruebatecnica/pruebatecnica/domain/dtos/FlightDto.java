package com.pruebatecnica.pruebatecnica.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightDto {
    private int id;

    @JsonProperty("DepartureStation")
    private String departureStation;

    @JsonProperty("ArrivalStation")
    private String arrivalStation;

    @JsonProperty("Price")
    private Double price;

    @JsonProperty("FlightCarrier")
    private String flightCarrier;

    @JsonProperty("FlightNumber")
    private String flightNumber;

    public FlightDto() {
    }

    public FlightDto(int id, String departureStation, String arrivalStation, Double price, String flightCarrier,
            String flightNumber) {
        this.id = id;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.price = price;
        this.flightCarrier = flightCarrier;
        this.flightNumber = flightNumber;
    }

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

    public String getFlightCarrier() {
        return this.flightCarrier;
    }

    public void setFlightCarrier(String flightCarrier) {
        this.flightCarrier = flightCarrier;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
