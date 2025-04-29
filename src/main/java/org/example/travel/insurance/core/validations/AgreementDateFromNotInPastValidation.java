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
class AgreementDateFromNotInPastValidation implements TravelRequestValidation {

    private final DateTimeService dataTimeService;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDate = dataTimeService.getCurrentDateTime();

        return (dateFrom != null && dateFrom.before(currentDate))
                ? Optional.of(new ValidationError("agreementDateFrom", "AgreementDateFrom must not be in the past!"))
                : Optional.empty();
    }

}
