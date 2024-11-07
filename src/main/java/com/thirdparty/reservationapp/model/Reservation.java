package com.thirdparty.reservationapp.model;


import lombok.Data;

@Data
public class Reservation {
    private Long bookingId;
    private String passengerName;
    private String transportType;  // "RAILWAY", "BUS", "FLIGHT"
    private String destinationCity;

    public Reservation(Long bookingId, String passengerName, String transportType, String destinationCity) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.transportType = transportType;
        this.destinationCity = destinationCity;
    }
}
