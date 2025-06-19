package org.example.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.util.DateTimeUtil;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PersonBirthDateInThePastValidation extends TravelPersonFieldValidationImpl {

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO personDTO){
        Date birthDate = personDTO.getPersonBirthDate();
        Date currentDate = dateTimeUtil.getCurrentDateTime();

        return (birthDate != null && birthDate.after(currentDate))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreementDTO, PersonDTO person) {
        return null;
    }

}
