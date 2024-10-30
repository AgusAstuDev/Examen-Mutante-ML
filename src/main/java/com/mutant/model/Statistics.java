package com.mutant.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Statistics {
    private final Integer countMutantDna;
    private final Integer countHumanDna;

    /**
     * @return Retorna el ratio entre el número de secuencias de ADN mutantes y humanas.
     */
    public double getRatio() {
        return countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
    }
}
