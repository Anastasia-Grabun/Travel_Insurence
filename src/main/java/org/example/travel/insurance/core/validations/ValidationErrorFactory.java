package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.util.ErrorCodeUtil;
import org.example.travel.insurance.core.util.Placeholder;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorCodeUtil messageProvider;

    ValidationError buildError(String errorCode){
        String description = messageProvider.getDescription(errorCode);
        return new ValidationError(errorCode, description);
    }

    ValidationError buildError(String errorCode, List<Placeholder> placeholders){
        String description = messageProvider.getErrorDescription(errorCode, placeholders);
        return new ValidationError(errorCode, description);
    }

}
