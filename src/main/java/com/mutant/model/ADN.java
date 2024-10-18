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

    @ElementCollection // Para manejar la colección de cadenas
    @Column(name = "fila", columnDefinition = "TEXT") // nombre de la columna
    private List<String> adn; // Ahora almacena una lista de cadenas

    private int isMutant;
    private Long accumulate;
}
