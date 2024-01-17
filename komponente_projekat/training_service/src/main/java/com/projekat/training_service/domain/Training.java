package com.projekat.training_service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Training(Gymnasium gym, TrainingType trainingType, BigDecimal price) {
        this.gym = gym;
        this.trainingType = trainingType;
        this.price = price;
    }

}
