package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class EmptySelectedRisksValidation implements TravelRequestValidation{

    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

}
