package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.ValidationError;
import java.util.List;

public interface TravelCalculatePremiumRequestValidator {

    List<ValidationError> validate(TravelCalculatePremiumRequestV1 request);

}
