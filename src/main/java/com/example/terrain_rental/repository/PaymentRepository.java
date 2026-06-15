package com.example.terrain_rental.repository;

import com.example.terrain_rental.model.Payment;
import com.example.terrain_rental.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Get the payment(s) associated with a booking
    List<Payment> findByBookingId(Long bookingId);

    // Find a payment by its transaction id (e.g. for payment gateway webhooks)
    Optional<Payment> findByTransactionId(String transactionId);

    // Filter payments by status (e.g. list all "refunded" payments for admin)
    List<Payment> findByStatus(PaymentStatus status);
}