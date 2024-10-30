package com.mutant.service;

import com.mutant.model.ADN;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ADNServiceInterface {
    ADN saveADN(ADN adn);
    Optional<ADN> getADNById(Long id);
    Mono<Boolean> isMutant(String[] adn);
}
