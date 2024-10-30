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

    @Column(name = "is_mutant")
    private Boolean isMutant;

    @Column(name = "dna_sequence", unique = true)
    private String dnaSequence;
}
