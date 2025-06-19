package org.example.core.validations;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {

    Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person);

    List<ValidationErrorDTO> validateList(AgreementDTO agreement, PersonDTO person);

}
