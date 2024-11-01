package com.mutant.service;

import com.mutant.controller.MutantController;
import com.mutant.model.ADN;
import com.mutant.model.ADNRequest;
import com.mutant.model.Statistics;
import com.mutant.repository.ADNRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class ADNServiceTest {

    @Mock
    private ADNRepository adnRepository;

    @InjectMocks
    private ADNService adnService;

    @InjectMocks
    private MutantController mutantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutantReturnsTrueForMutantADN() {
        String[] mutantADN = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        when(adnRepository.findByAdnSequence(any())).thenReturn(Optional.empty());

        Mono<Boolean> result = adnService.isMutant(mutantADN);

        assertTrue(result.block());
        verify(adnRepository, times(1)).save(any(ADN.class));
    }

    @Test
    public void testIsMutantReturnsFalseForHumanADN() {
        String[] humanADN = {"ATGCGA", "CAGTGC", "TTATDT", "AGAGGG", "CCCTTA", "TCACTG"};
        when(adnRepository.findByAdnSequence(any())).thenReturn(Optional.empty());

        Mono<Boolean> result = adnService.isMutant(humanADN);

        assertFalse(result.block());
        verify(adnRepository, times(1)).save(any(ADN.class));
    }


    @Test
    public void testGetStatisticsReturnsZeroRatioWhenNoHumanADN() {
        when(adnRepository.countByIsMutant(true)).thenReturn(10L);
        when(adnRepository.countByIsMutant(false)).thenReturn(0L);

        Statistics stats = adnService.getStatistics();

        assertEquals(10L, stats.getCountMutantAdn());
        assertEquals(0L, stats.getCountHumanAdn());
        assertEquals(0.0, stats.getRatio(), 0.0001);
    }

    @Test
    public void testIsMutantSavesCorrectADNDetails() {
        String[] mutantADN = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        when(adnRepository.findByAdnSequence(any())).thenReturn(Optional.empty());

        adnService.isMutant(mutantADN).block();

        ArgumentCaptor<ADN> captor = ArgumentCaptor.forClass(ADN.class);
        verify(adnRepository).save(captor.capture());

        ADN capturedAdn = captor.getValue();
        assertTrue(capturedAdn.getIsMutant());
        assertEquals("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG", capturedAdn.getAdnSequence());
    }

    @Test
    public void testADNRequestGettersAndSetters() {
        ADNRequest adnRequest = new ADNRequest();

        String[] adn = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        adnRequest.setAdn(adn);

        assertArrayEquals(adn, adnRequest.getAdn());
    }

//    @Test
//    public void testMutantAppMainMethod() {
//        // Verificamos que el método main de MutantApp se ejecuta sin lanzar excepciones
//        assertDoesNotThrow(() -> MutantApp.main(new String[]{}));
//    }
    
}
