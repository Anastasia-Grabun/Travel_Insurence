package org.example.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.util.Placeholder;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PersonCodeFormatValidation extends TravelPersonFieldValidationImpl{

    private final ValidationErrorFactory errorFactory;
    private static final Pattern PERSON_CODE_PATTERN = Pattern.compile("^\\d{6}-\\d{5}$");

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        if (!isPersonCodeNullOrBlank(person) && !isValidFormat(person)) {
            Placeholder placeholder = new Placeholder("PERSONAL_CODE", person.getPersonCode());
            return Optional.of(errorFactory.buildError("ERROR_CODE_20", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }

    private boolean isPersonCodeNullOrBlank(PersonDTO person) {
        return person.getPersonCode() == null || person.getPersonCode().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        return PERSON_CODE_PATTERN.matcher(person.getPersonCode()).matches();
    }

}
