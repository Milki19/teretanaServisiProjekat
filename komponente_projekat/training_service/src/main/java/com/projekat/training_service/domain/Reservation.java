package com.projekat.training_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Appointment appointment;
    private Long userId;
    private BigDecimal price;

    public Reservation(Appointment appointment, Long userId, BigDecimal price) {
        this.appointment = appointment;
        this.userId = userId;
        this.price = price;
    }

}
