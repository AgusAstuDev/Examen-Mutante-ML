package com.mutant.model;

import lombok.Data;

@Data
public class Statistics {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}
