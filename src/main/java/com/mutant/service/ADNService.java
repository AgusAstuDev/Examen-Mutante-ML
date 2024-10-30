package com.mutant.service;

import com.mutant.model.ADN;
import com.mutant.model.Statistics;
import com.mutant.repository.ADNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ADNService implements ADNServiceInterface {

    private static final int REQUIRED_SEQUENCE_LENGTH = 4;

    @Autowired
    private ADNRepository adnRepository;

    public ADN saveADN(ADN adn) {
        return adnRepository.save(adn);
    }

    public Optional<ADN> getADNById(Long id) {
        return adnRepository.findById(id);
    }

    @Transactional
    public Mono<Boolean> isMutant(String[] adnArray) {
        List<String> secuencia = List.of(adnArray);
        boolean isMutant = hasMutantSequence(secuencia);

        saveADNIfNotExists(adnArray, isMutant);

        return Mono.just(isMutant);
    }
    // Funcion que guarda en la DB si ese ADN no existe anteriormente
    private void saveADNIfNotExists(String[] adnArray, boolean isMutant) {
        String adnString = String.join(",", adnArray);
        if (adnRepository.findByAdnSequence(adnString).isEmpty()) {
            ADN adn = new ADN();
            adn.setAdnSequence(adnString);
            adn.setIsMutant(isMutant);
            adnRepository.save(adn);
        }
    }
    // Funcion que verifica si es mutante
    private boolean hasMutantSequence(List<String> adn) {
        return checkHorizontalAndVertical(adn) || checkDiagonals(adn);
    }
    // Funcion que checkea si hay coincidencias de letras horizontales o verticales
    private boolean checkHorizontalAndVertical(List<String> adn) {
        int n = adn.size();
        for (int i = 0; i < n; i++) {
            if (checkSequence(adn.get(i), REQUIRED_SEQUENCE_LENGTH)) return true;
            StringBuilder verticalSequence = new StringBuilder();
            for (int j = 0; j < n; j++) {
                verticalSequence.append(adn.get(j).charAt(i));
            }
            if (checkSequence(verticalSequence.toString(), REQUIRED_SEQUENCE_LENGTH)) return true;
        }
        return false;
    }
    // Funcion que checkea si hay coincidencias de letras diagonales o contra-diagonales
    private boolean checkDiagonals(List<String> adn) {
        int n = adn.size();
        for (int i = 0; i <= n - REQUIRED_SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - REQUIRED_SEQUENCE_LENGTH; j++) {
                if (checkDiagonal(adn, i, j, true) || checkDiagonal(adn, i, j, false)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(List<String> adn, int startRow, int startCol, boolean leftToRight) {
        int count = 1;
        for (int k = 1; k < REQUIRED_SEQUENCE_LENGTH; k++) {
            int row = startRow + k;
            int col = leftToRight ? startCol + k : startCol + REQUIRED_SEQUENCE_LENGTH - k - 1;
            if (adn.get(row).charAt(col) == adn.get(startRow).charAt(startCol)) {
                count++;
                if (count >= REQUIRED_SEQUENCE_LENGTH) return true;
            } else {
                break;
            }
        }
        return false;
    }

    private boolean checkSequence(String sequence, int requiredLength) {
        int count = 1;

        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == sequence.charAt(i - 1)) {
                count++;
                if (count >= requiredLength) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }

    public Statistics getStatistics() {
        long countMutantAdn = adnRepository.countByIsMutant(true);
        long countHumanAdn = adnRepository.countByIsMutant(false);

        double ratio = countHumanAdn > 0 ? (double) countMutantAdn / countHumanAdn : 0;

        Statistics stats = new Statistics();
        stats.setCountMutantAdn(countMutantAdn);
        stats.setCountHumanAdn(countHumanAdn);
        stats.setRatio(ratio);
        return stats;
    }

}
