package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class EmptyMedicalRiskLimitLevelValidation implements TravelRequestValidation{

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (isMedicalRiskLimitLevelEnabled()
                && containsTravelMedical(request)
                && isMedicalRiskLimitLevelIsNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    private boolean isMedicalRiskLimitLevelEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private boolean containsTravelMedical(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private boolean isMedicalRiskLimitLevelIsNullOrBlank(TravelCalculatePremiumRequest request) {
        return request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank();
    }

}
