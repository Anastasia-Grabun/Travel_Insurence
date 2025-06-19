package org.example.core.validations.agreement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.repositories.ClassifierValueRepository;
import org.example.core.util.Placeholder;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class SelectedRiskValidation extends TravelAgreementFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;
    private final ClassifierValueRepository classifierValueRepository;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        List<String> selected_risks = agreement.getSelectedRisks();
        return selected_risks == null ? List.of() : validateSelectedRisks(selected_risks);
    }

    private List<ValidationErrorDTO> validateSelectedRisks(List<String> selectedRisks) {
        log.info("Selected risks for validation: {}", selectedRisks);
        return selectedRisks.stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationErrorDTO> validateRiskIc(String riskIc){
        boolean exists = existInDatabase(riskIc);
        log.info("Validating riskIc: {}, exists in DB: {}", riskIc, exists);

        return !exists
                ? Optional.of(buildValidationError(riskIc))
                : Optional.empty();
    }


    private ValidationErrorDTO buildValidationError(String riskIc){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

}
