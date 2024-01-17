package com.projekat.training_service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDate date;
    private String startTime;
    private String endTime;
    @ManyToOne
    private Training training;
    private Integer capacity;


    public Appointment(LocalDate date, String startTime, String endTime, Training training, Integer capacity) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.training = training;
        this.capacity = capacity;
    }


}
