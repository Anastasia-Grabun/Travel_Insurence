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
class PersonLastNameFormatValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;
    private static final Pattern PERSON_LAST_NAME_PATTERN = Pattern.compile("^[A-Za-z]+([ -][A-Za-z]+)*$");


    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        if (!isPersonLastNameNullOrBlank(person) && !isValidFormat(person)) {
            Placeholder placeholder = new Placeholder("PERSON_LAST_NAME", person.getPersonLastName());
            return Optional.of(errorFactory.buildError("ERROR_CODE_21", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }

    private boolean isPersonLastNameNullOrBlank(PersonDTO person) {
        return person.getPersonLastName() == null || person.getPersonLastName().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        return PERSON_LAST_NAME_PATTERN.matcher(person.getPersonLastName()).matches();
    }

}
