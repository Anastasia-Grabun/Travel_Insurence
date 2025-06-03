package org.example.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.insurance.core.repositories.ClassifierValueRepository;
import org.example.travel.insurance.core.util.Placeholder;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class SelectedRiskValidation implements TravelRequestListValidation{

    private final ValidationErrorFactory errorFactory;
    private final ClassifierValueRepository classifierValueRepository;

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        List<String> selected_risks = request.getSelectedRisks();
        return selected_risks == null ? List.of() : validateSelectedRisks(selected_risks);
    }

    private List<ValidationError> validateSelectedRisks(List<String> selectedRisks) {
        log.info("Selected risks for validation: {}", selectedRisks);
        return selectedRisks.stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationError> validateRiskIc(String riskIc){
        boolean exists = existInDatabase(riskIc);
        log.info("Validating riskIc: {}, exists in DB: {}", riskIc, exists);

        return !exists
                ? Optional.of(buildValidationError(riskIc))
                : Optional.empty();
    }


    private ValidationError buildValidationError(String riskIc){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

}
