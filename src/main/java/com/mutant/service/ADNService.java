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

        // Convertimos el array de ADN en un String para almacenar en la base de datos
        String adnString = String.join(",", adnArray);
        Optional<ADN> existingAdn = adnRepository.findByAdnSequence(adnString);

        if (existingAdn.isEmpty()) {
            ADN adn = new ADN();
            adn.setAdnSequence(adnString);
            adn.setIsMutant(isMutant);
            adnRepository.save(adn);
        }

        return Mono.just(isMutant);
    }

    private boolean hasMutantSequence(List<String> adn) {
        int n = adn.size();
        int requiredSequenceLength = 4;

        // Horizontal y Vertical
        for (int i = 0; i < n; i++) {
            int horizontalCount = 1;
            int verticalCount = 1;

            for (int j = 1; j < n; j++) {
                // Horizontal
                if (adn.get(i).charAt(j) == adn.get(i).charAt(j - 1)) {
                    horizontalCount++;
                    if (horizontalCount >= requiredSequenceLength) return true;
                } else {
                    horizontalCount = 1;
                }

                // Vertical
                if (adn.get(j).charAt(i) == adn.get(j - 1).charAt(i)) {
                    verticalCount++;
                    if (verticalCount >= requiredSequenceLength) return true;
                } else {
                    verticalCount = 1;
                }
            }
        }

        // Diagonales (Oblicua y Contra-oblicua)
        for (int i = 0; i <= n - requiredSequenceLength; i++) {
            for (int j = 0; j <= n - requiredSequenceLength; j++) {
                // Oblicua (diagonal de izquierda a derecha)
                int diagonalCount = 1;
                for (int k = 1; k < requiredSequenceLength; k++) {
                    if (adn.get(i + k).charAt(j + k) == adn.get(i + k - 1).charAt(j + k - 1)) {
                        diagonalCount++;
                        if (diagonalCount >= requiredSequenceLength) return true;
                    } else {
                        break;
                    }
                }

                // Contra-oblicua (diagonal de derecha a izquierda)
                int antiDiagonalCount = 1;
                for (int k = 1; k < requiredSequenceLength; k++) {
                    if (adn.get(i + k).charAt(j + requiredSequenceLength - k - 1) == adn.get(i + k - 1).charAt(j + requiredSequenceLength - k)) {
                        antiDiagonalCount++;
                        if (antiDiagonalCount >= requiredSequenceLength) return true;
                    } else {
                        break;
                    }
                }
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
