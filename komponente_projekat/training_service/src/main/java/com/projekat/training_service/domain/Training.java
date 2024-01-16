package com.projekat.training_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Training {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Gymnasium gym;
    @ManyToOne
    private TrainingType trainingType;
    private BigDecimal price;

}
