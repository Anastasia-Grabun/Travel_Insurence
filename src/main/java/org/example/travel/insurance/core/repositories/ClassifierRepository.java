package org.example.travel.insurance.core.repositories;

import org.example.travel.insurance.core.domain.Classifier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    Optional<Classifier> findByTitle(String title);

}
