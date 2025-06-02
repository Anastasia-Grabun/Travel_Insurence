package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import java.util.List;

interface TravelRequestListValidation {

    List<ValidationError> validateList(TravelCalculatePremiumRequest request);

}
