package org.example.core.validations;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelAgreementFieldValidation {

    Optional<ValidationErrorDTO> validate(AgreementDTO agreement);

    List<ValidationErrorDTO> validateList(AgreementDTO agreement);

}