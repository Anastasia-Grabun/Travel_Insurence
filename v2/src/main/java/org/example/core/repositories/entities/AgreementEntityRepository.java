package org.example.core.repositories.entities;

import org.example.core.domain.entities.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {
    Optional<AgreementEntity> findByUuid(String uuid);

}
