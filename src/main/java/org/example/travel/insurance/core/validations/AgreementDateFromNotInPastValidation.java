package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.util.DateTimeUtil;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateFromNotInPastValidation implements TravelRequestValidation {

    private final DateTimeUtil dataTimeService;
    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDate = dataTimeService.getCurrentDateTime();

        return (dateFrom != null && dateFrom.before(currentDate))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }

}
