package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.ErrorMessageProvider;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorMessageProvider messageProvider;

    ValidationError buildError(String errorCode){
        String description = messageProvider.getDescription(errorCode);
        return new ValidationError(errorCode, description);
    }

}
