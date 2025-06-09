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
class PersonBirthDateInThePastValidation implements TravelRequestValidation{

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory errorFactory;

    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request){
        Date birthDate = request.getPersonBirthDate();
        Date currentDate = dateTimeUtil.getCurrentDateTime();

        return (birthDate != null && birthDate.after(currentDate))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

}
