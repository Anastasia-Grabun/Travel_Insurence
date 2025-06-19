package org.example.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.validations.TravelRequestValidation;
import org.example.travel.insurance.core.validations.ValidationErrorFactory;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class PersonFirstNameValidation implements TravelRequestValidation {

    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

}
