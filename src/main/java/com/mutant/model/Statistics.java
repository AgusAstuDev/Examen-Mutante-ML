package com.mutant.model;

import lombok.Data;

@Data
public class Statistics {
    private long countMutantAdn;
    private long countHumanAdn;
    private double ratio;
}
