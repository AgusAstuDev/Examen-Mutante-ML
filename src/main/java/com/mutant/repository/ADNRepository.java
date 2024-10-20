package com.mutant.repository;

import com.mutant.model.ADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADNRepository extends JpaRepository<ADN, Long> {
    // Aquí puedes añadir métodos personalizados si es necesario
}
