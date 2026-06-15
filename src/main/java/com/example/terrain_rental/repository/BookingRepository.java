package com.example.terrain_rental.repository;

import com.example.terrain_rental.model.Booking;
import com.example.terrain_rental.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // All bookings made by a specific renter
    List<Booking> findByRenterId(Long renterId);

    // All bookings for a specific terrain (e.g. for the owner to manage)
    List<Booking> findByTerrainId(Long terrainId);

    // Bookings filtered by status (e.g. all "pending" requests for an owner to approve)
    List<Booking> findByTerrainIdAndStatus(Long terrainId, BookingStatus status);

    // Check for overlapping bookings on a terrain for given dates
    // (useful for validating new booking requests before saving)
    List<Booking> findByTerrainIdAndStatusInAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long terrainId,
            List<BookingStatus> statuses,
            LocalDate endDate,
            LocalDate startDate
    );
}