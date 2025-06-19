package org.example.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.util.ErrorCodeUtil;
import org.example.core.util.Placeholder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorCodeUtil messageProvider;

    public ValidationErrorDTO buildError(String errorCode){
        String description = messageProvider.getErrorDescription(errorCode);
        return new ValidationErrorDTO(errorCode, description);
    }

    public ValidationErrorDTO buildError(String errorCode, List<Placeholder> placeholders){
        String description = messageProvider.getErrorDescription(errorCode, placeholders);
        return new ValidationErrorDTO(errorCode, description);
    }

}
