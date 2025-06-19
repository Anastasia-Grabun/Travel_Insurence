package org.example.core.repositories;

import org.example.core.domain.MedicalRiskLimitLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicalRiskLimitLevelRepository
        extends JpaRepository<MedicalRiskLimitLevel, Long> {

    Optional<MedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);

}
