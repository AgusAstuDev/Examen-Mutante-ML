package com.mutant.controller;

import com.mutant.model.ADNRequest;
import com.mutant.service.ADNService;

import com.mutant.model.ADN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MutantController {

    private final ADNService adnService;

    @Autowired
    public MutantController(ADNService adnService) {
        this.adnService = adnService;
    }

    @PostMapping("/mutant")
    public Mono<ResponseEntity<String>> postIsMutant(@RequestBody ADNRequest request) {
        return adnService.isMutant(request.getAdn())
                .map(isMutant -> isMutant
                        ? ResponseEntity.status(HttpStatus.OK).body("Es Mutante")
                        : ResponseEntity.status(HttpStatus.FORBIDDEN).body("Es Humano"));
    }

}
