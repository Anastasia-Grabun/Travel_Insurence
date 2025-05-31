package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import java.util.Optional;

interface TravelRequestValidation {

    Optional<ValidationError> validate(TravelCalculatePremiumRequest request);

}
