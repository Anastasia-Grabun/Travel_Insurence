package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{

    private final List<TravelRequestValidation> travelValidations;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {

        return travelValidations.stream()
                .flatMap(validation -> validation.validate(request).stream())
                .collect(Collectors.toList());
    }

}
