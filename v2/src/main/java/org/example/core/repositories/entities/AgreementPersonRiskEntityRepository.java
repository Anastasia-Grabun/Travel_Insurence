package org.example.core.repositories.entities;

import org.example.core.domain.entities.AgreementPersonEntity;
import org.example.core.domain.entities.AgreementPersonRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgreementPersonRiskEntityRepository extends JpaRepository<AgreementPersonRiskEntity, Long> {

    List<AgreementPersonRiskEntity> findByAgreementPerson(AgreementPersonEntity agreementPerson);

}
