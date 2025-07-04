package org.example.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPersonFieldValidator {

    private final List<TravelPersonFieldValidation> personFieldValidations;

    List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(person -> collectPersonErrors(agreement, person))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectPersonErrors(AgreementDTO agreement, PersonDTO person) {
        List<ValidationErrorDTO> singleErrors = collectSinglePersonErrors(agreement, person);
        List<ValidationErrorDTO> listErrors = collectListPersonErrors(agreement, person);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDTO> collectSinglePersonErrors(AgreementDTO agreement, PersonDTO person) {
        return personFieldValidations.stream()
                .map(validation -> validation.validate(agreement, person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListPersonErrors(AgreementDTO agreement, PersonDTO person) {
        return personFieldValidations.stream()
                .map(validation -> validation.validateList(agreement, person))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> singleErrors,
                                                      List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
