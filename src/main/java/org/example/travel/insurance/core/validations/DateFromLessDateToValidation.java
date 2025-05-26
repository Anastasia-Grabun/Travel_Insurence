package org.example.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class DateFromLessDateToValidation implements TravelRequestValidation {

    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();

        return (dateFrom != null && dateTo != null)
                && (dateTo.before(dateFrom) || dateFrom.equals(dateTo))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

}
