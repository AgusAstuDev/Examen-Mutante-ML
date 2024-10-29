package com.mutant.service;

import com.mutant.model.ADN;
import com.mutant.repository.ADNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ADNService implements ADNServiceInterface {

    @Autowired
    private ADNRepository adnRepository;

    // Método para guardar un ADN en la base de datos
    public ADN saveADN(ADN adn) {
        return adnRepository.save(adn);
    }

    // Método para encontrar un ADN por su id
    public Optional<ADN> getADNById(Long id) {
        return adnRepository.findById(id);
    }


    public boolean isMutant(ADN adn) {
        List<String> secuencia = adn.getAdn();
        return hasMutantSequence(secuencia);
    }

    private boolean hasMutantSequence(List<String> adn) {
        int consecutiveCount;

        // Horizontal
        for (String row : adn) {
            consecutiveCount = 1;
            for (int i = 1; i < row.length(); i++) {
                if (row.charAt(i) == row.charAt(i - 1)) {
                    consecutiveCount++;
                    if (consecutiveCount >= 4) return true; // Se encontró una secuencia de 4 iguales
                } else {
                    consecutiveCount = 1; // Reinicia el conteo
                }
            }
        }

        // Vertical
        for (int i = 0; i < adn.get(0).length(); i++) { // Cambiado para usar List
            consecutiveCount = 1;
            for (int j = 1; j < adn.size(); j++) { // Cambiado para usar List
                if (adn.get(j).charAt(i) == adn.get(j - 1).charAt(i)) {
                    consecutiveCount++;
                    if (consecutiveCount >= 4) return true; // Se encontró una secuencia de 4 iguales
                } else {
                    consecutiveCount = 1; // Reinicia el conteo
                }
            }
        }

        // Puedes agregar lógica para verificar oblicuamente si lo deseas

        return false; // No se encontraron secuencias mutantes
    }
}
