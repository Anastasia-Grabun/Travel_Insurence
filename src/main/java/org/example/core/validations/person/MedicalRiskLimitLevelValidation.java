package org.example.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.repositories.ClassifierValueRepository;
import org.example.core.util.Placeholder;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return (isMedicalRiskLimitLevelNotBlank(person))
                && !existInDatabase(person.getMedicalRiskLimitLevel())
                ? Optional.of(buildValidationError(person.getMedicalRiskLimitLevel()))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String medicalRiskLimitLevel) {
        Placeholder placeholder = new Placeholder("NOT_SUPPORTED_MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevel);
        return errorFactory.buildError("ERROR_CODE_14", List.of(placeholder));
    }

    private boolean isMedicalRiskLimitLevelNotBlank(PersonDTO personDto) {
        return personDto.getMedicalRiskLimitLevel() != null && !personDto.getMedicalRiskLimitLevel().isBlank();
    }

    private boolean existInDatabase(String medicalRiscLimitLevelIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", medicalRiscLimitLevelIc).isPresent();
    }

}
