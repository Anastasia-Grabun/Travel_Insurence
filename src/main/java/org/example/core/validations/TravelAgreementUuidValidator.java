package org.example.core.validations;

import org.example.core.api.dto.ValidationErrorDTO;
import java.util.List;

public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
