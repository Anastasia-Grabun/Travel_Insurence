package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.util.DateTimeUtil;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToNotInPastValidation implements TravelRequestValidation {

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request){
        Date dateTo = request.getAgreementDateTo();
        Date currentDate = dateTimeUtil.getCurrentDateTime();

        return (dateTo != null && dateTo.before(currentDate))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }

}
