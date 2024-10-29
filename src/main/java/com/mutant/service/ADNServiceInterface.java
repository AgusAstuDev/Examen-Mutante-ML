package com.mutant.service;

import com.mutant.model.ADN;

import java.util.Optional;

public interface ADNServiceInterface {
    ADN saveADN(ADN adn);
    Optional<ADN> getADNById(Long id);
    boolean isMutant(ADN adn);
}
