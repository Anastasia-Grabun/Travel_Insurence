package org.example.core.repositories.entities;

import org.example.core.domain.entities.AgreementEntity;
import org.example.core.domain.entities.SelectedRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SelectedRiskEntityRepository extends JpaRepository<SelectedRiskEntity, Long> {

    List<SelectedRiskEntity> findByAgreement(AgreementEntity agreement);

}
