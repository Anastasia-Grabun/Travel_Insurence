package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.DateTimeService;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToNotInPastValidation implements TravelRequestValidation {

    private final DateTimeService dateTimeService;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
        Date dateTo = request.getAgreementDateTo();
        Date currentDate = dateTimeService.getCurrentDateTime();

        return (dateTo != null && dateTo.before(currentDate))
                ? Optional.of(new ValidationError("agreementDateTo", "AgreementDateTo must not be in the past!"))
                : Optional.empty();
    }

}
