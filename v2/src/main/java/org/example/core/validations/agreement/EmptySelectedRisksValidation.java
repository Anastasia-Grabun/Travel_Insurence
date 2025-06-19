package org.example.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class EmptySelectedRisksValidation extends TravelAgreementFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (agreement.getSelectedRisks() == null || agreement.getSelectedRisks().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

}
