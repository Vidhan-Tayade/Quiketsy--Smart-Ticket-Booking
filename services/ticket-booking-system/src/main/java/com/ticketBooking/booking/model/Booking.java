package com.ticketBooking.booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID bookingId;

    @Column(name = "event_id", columnDefinition = "uuid", nullable = false)
    private UUID eventId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "order_id")
    private String orderId; // razorpay order id or internal order id

    @Column(name = "payment_id")
    private String paymentId; // razorpay payment id after success

    @Column(name = "amount")
    private Integer amount; // in paise

    @Column(name = "status")
    private String status; // PENDING, CONFIRMED, CANCELLED, QUEUED

    @Column(name = "queue_position")
    @Builder.Default
    private Integer queuePosition = 0;

    @Column(name = "qr_code", columnDefinition = "text")
    private String qrCodeUrl; // store base64 PNG or URL

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (bookingId == null) bookingId = UUID.randomUUID();
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = "PENDING";
        if (queuePosition == null) queuePosition = 0;
    }

    // getters and setters...
    // (generate via IDE or Lombok)
    // omitted here to save space but include in your file
    // e.g. getBookingId(), setBookingId(UUID bookingId), etc.
}
