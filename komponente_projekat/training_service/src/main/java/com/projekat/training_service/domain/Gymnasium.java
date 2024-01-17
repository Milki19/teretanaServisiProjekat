package com.projekat.training_service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Gymnasium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer numberOfCoaches;
    private Integer capacity;


    public Gymnasium(String name, String description, Integer numberOfCoaches, Integer capacity) {
        this.name = name;
        this.description = description;
        this.numberOfCoaches = numberOfCoaches;
        this.capacity = capacity;
    }

}
