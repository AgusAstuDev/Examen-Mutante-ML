package com.mutant.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "adn")
public class ADN implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(name = "fila", columnDefinition = "TEXT")
    private List<String> adn;

    private boolean isMutant;
    private Long countedMutations;
}
