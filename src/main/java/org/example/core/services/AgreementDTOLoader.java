package org.example.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.repositories.entities.AgreementEntityRepository;
import org.example.core.repositories.entities.AgreementPersonEntityRepository;
import org.example.core.repositories.entities.AgreementPersonRiskEntityRepository;
import org.example.core.repositories.entities.SelectedRiskEntityRepository;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.RiskDTO;
import org.example.core.domain.entities.AgreementEntity;
import org.example.core.domain.entities.AgreementPersonEntity;
import org.example.core.domain.entities.SelectedRiskEntity;
import org.example.core.repositories.entities.AgreementEntityRepository;
import org.example.core.repositories.entities.AgreementPersonEntityRepository;
import org.example.core.repositories.entities.AgreementPersonRiskEntityRepository;
import org.example.core.repositories.entities.SelectedRiskEntityRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDTOLoader {

    private final AgreementEntityRepository agreementEntityRepository;
    private final SelectedRiskEntityRepository selectedRiskEntityRepository;
    private final AgreementPersonEntityRepository agreementPersonEntityRepository;
    private final AgreementPersonRiskEntityRepository agreementPersonRiskEntityRepository;

    AgreementDTO load(String uuid) {
        AgreementDTO dto = new AgreementDTO();
        AgreementEntity agreement = agreementEntityRepository.findByUuid(uuid).get();
        loadAgreementFields(dto, agreement);
        loadSelectedRisks(dto, agreement);
        loadPersons(dto, agreement);

        return dto;
    }

    private void loadPersons(AgreementDTO dto, AgreementEntity agreement) {
        List<AgreementPersonEntity> personEntities = agreementPersonEntityRepository.findByAgreement(agreement);
        List<PersonDTO> persons = personEntities.stream()
                .map(personEntity -> {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonFirstName(personEntity.getPerson().getFirstName());
                    personDTO.setPersonLastName(personEntity.getPerson().getLastName());
                    personDTO.setPersonCode(personEntity.getPerson().getPersonCode());
                    personDTO.setPersonBirthDate(personEntity.getPerson().getBirthDate());
                    personDTO.setMedicalRiskLimitLevel(personEntity.getMedicalRiskLimitLevel());
                    personDTO.setTravelCost(personEntity.getTravelCost());

                    personDTO.setRisks(
                            agreementPersonRiskEntityRepository.findByAgreementPerson(personEntity)
                                    .stream()
                                    .map(agreementPersonRiskEntity -> {
                                        RiskDTO riskDTO = new RiskDTO();
                                        riskDTO.setRiskIc(agreementPersonRiskEntity.getRiskIc());
                                        riskDTO.setPremium(agreementPersonRiskEntity.getPremium());
                                        return riskDTO;
                                    })
                                    .collect(Collectors.toList())
                    );
                    return personDTO;
                })
                .collect(Collectors.toList());
        dto.setPersons(persons);
    }

    private void loadSelectedRisks(AgreementDTO dto, AgreementEntity agreement) {
        dto.setSelectedRisks(selectedRiskEntityRepository.findByAgreement(agreement)
                .stream().map(SelectedRiskEntity::getRiskIc)
                .collect(Collectors.toList()));
    }

    private void loadAgreementFields(AgreementDTO dto, AgreementEntity agreement) {
        dto.setUuid(agreement.getUuid());
        dto.setAgreementDateFrom(agreement.getAgreementDateFrom());
        dto.setAgreementDateTo(agreement.getAgreementDateTo());
        dto.setCountry(agreement.getCountry());
        dto.setAgreementPremium(agreement.getAgreementPremium());
    }

}
