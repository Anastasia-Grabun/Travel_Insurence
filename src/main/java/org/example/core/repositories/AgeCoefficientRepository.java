package org.example.core.repositories;

import org.example.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AgeCoefficientRepository
        extends JpaRepository<AgeCoefficient, Long> {

    @Query("SELECT ac from AgeCoefficient ac " +
            "where ac.ageFrom <= :age " +
            "and ac.ageTo >= :age")
    Optional<AgeCoefficient> findCoefficient(@Param("age") Integer age);

}
