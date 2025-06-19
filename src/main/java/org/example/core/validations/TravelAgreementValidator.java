package org.example.core.validations;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import java.util.List;

public interface TravelAgreementValidator {

    List<ValidationErrorDTO> validate(AgreementDTO agreementDTO);

}
