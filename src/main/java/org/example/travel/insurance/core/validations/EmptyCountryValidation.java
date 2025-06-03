package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class EmptyCountryValidation implements TravelRequestValidation {

    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (containsTravelMedicalRisk(request) && countryIsNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean containsTravelMedicalRisk(TravelCalculatePremiumRequest request){
        return request != null &&
                request.getSelectedRisks() != null &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean countryIsNullOrBlank(TravelCalculatePremiumRequest request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

}
