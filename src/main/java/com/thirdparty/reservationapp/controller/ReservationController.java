package com.thirdparty.reservationapp.controller;


import com.thirdparty.reservationapp.model.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong bookingIdCounter = new AtomicLong();

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        reservation.setBookingId(bookingIdCounter.incrementAndGet());
        reservations.add(reservation);
        return reservation;
    }

    @PutMapping("/{bookingId}")
    public Reservation updateReservation(@PathVariable Long bookingId, @RequestBody Reservation updatedReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getBookingId().equals(bookingId)) {
                updatedReservation.setBookingId(bookingId);
                reservations.set(i, updatedReservation);
                return updatedReservation;
            }
        }
        return null;
    }

    @GetMapping("/{bookingId}")
    public Reservation getReservationById(@PathVariable Long bookingId) {
        return reservations.stream()
                .filter(reservation -> reservation.getBookingId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{bookingId}")
    public boolean deleteReservation(@PathVariable Long bookingId) {
        return reservations.removeIf(reservation -> reservation.getBookingId().equals(bookingId));
    }
}
