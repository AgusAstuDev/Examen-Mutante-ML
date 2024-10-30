package com.mutant.repository;

import com.mutant.model.ADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ADNRepository extends JpaRepository<ADN, Long> {
    Optional<ADN> findByDnaSequence(String dnaSequence);

    long countByIsMutant(boolean isMutant);
}
