package com.mutant.controller;

import com.mutant.service.ADNService;

import com.mutant.model.ADN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final ADNService adnService;

    @Autowired
    public MutantController(ADNService mutantService) {
        this.adnService = mutantService;
    }

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody ADN adn) {
        boolean isMutant = adnService.isMutant(adn);
        if (isMutant) {
            return new ResponseEntity<>("Es mutante", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No es mutante", HttpStatus.FORBIDDEN);
        }
    }
}
