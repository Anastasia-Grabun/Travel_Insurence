package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
class DateFromLessDateToValidation implements TravelRequestValidation {

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();

        return (dateFrom != null && dateTo != null)
                && (dateTo.before(dateFrom) || dateFrom.equals(dateTo))
                ? Optional.of(new ValidationError("agreementDateTo", "AgreementDateTo must be after AgreementDateFrom!"))
                : Optional.empty();
    }

}
