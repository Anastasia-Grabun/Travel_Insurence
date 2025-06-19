package org.example.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.domain.entities.AgreementEntity;
import org.example.core.domain.entities.AgreementPersonEntity;
import org.example.core.domain.entities.PersonEntity;
import org.example.core.domain.entities.SelectedRiskEntity;
import org.example.core.repositories.entities.AgreementEntityRepository;
import org.example.core.repositories.entities.AgreementPersonEntityRepository;
import org.example.core.repositories.entities.SelectedRiskEntityRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementEntityFactory {

    private final AgreementEntityRepository agreementEntityRepository;
    private final PersonEntityFactory personEntityFactory;
    private final SelectedRiskEntityRepository selectedRiskEntityRepository;
    private final AgreementPersonEntityRepository agreementPersonEntityRepository;

    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = saveAgreement(agreementDTO);
        saveAllSelectedRisks(agreementDTO, agreementEntity);
        saveAllAgreementPersons(agreementDTO, agreementEntity);
        return agreementEntity;
    }

    private AgreementEntity saveAgreement(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setUuid(UUID.randomUUID().toString());
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setAgreementPremium(agreementDTO.getAgreementPremium());

        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllSelectedRisks(AgreementDTO agreementDTO,
                                      AgreementEntity agreementEntity) {
        agreementDTO.getSelectedRisks().forEach(riskIc -> {
            SelectedRiskEntity riskEntity = new SelectedRiskEntity();
            riskEntity.setAgreement(agreementEntity);
            riskEntity.setRiskIc(riskIc);
            selectedRiskEntityRepository.save(riskEntity);
        });
    }

    private void saveAllAgreementPersons(AgreementDTO agreementDTO,
                                         AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(personDTO -> {
            PersonEntity personEntity = personEntityFactory.createPersonEntity(personDTO);
            AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
            agreementPersonEntity.setAgreement(agreementEntity);
            agreementPersonEntity.setPerson(personEntity);
            agreementPersonEntity.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
            agreementPersonEntityRepository.save(agreementPersonEntity);
        });
    }

}

