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
class PersonFirstNameFormatValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;
    private static final Pattern PERSON_FIRST_NAME_PATTERN = Pattern.compile("^[A-Za-z]+([ -][A-Za-z]+)*$");

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        if (!isPersonFirstNameNullOrBlank(person) && !isValidFormat(person)) {
            Placeholder placeholder = new Placeholder("PERSON_FIRST_NAME", person.getPersonFirstName());
            return Optional.of(errorFactory.buildError("ERROR_CODE_22", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }

    private boolean isPersonFirstNameNullOrBlank(PersonDTO person) {
        return person.getPersonFirstName() == null || person.getPersonFirstName().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        return PERSON_FIRST_NAME_PATTERN.matcher(person.getPersonFirstName()).matches();
    }

}
