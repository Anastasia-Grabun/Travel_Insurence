package org.example.core.validations.agreement;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.TravelAgreementFieldValidation;
import java.util.List;
import java.util.Optional;

abstract class TravelAgreementFieldValidationImpl implements TravelAgreementFieldValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        return null;
    }


}
