package org.example.core.validations.person;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.util.Placeholder;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PersonFieldAnnotationValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement, PersonDTO person) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        if (violations.isEmpty()) {
            return List.of();
        } else {
            List<ValidationErrorDTO> errors = new ArrayList<>();
            for (ConstraintViolation<PersonDTO> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                String fieldMessage = violation.getMessage();
                Placeholder placeholder1 = new Placeholder("FIELD_NAME", fieldName);
                Placeholder placeholder2 = new Placeholder("FIELD_MESSAGE", fieldMessage);
                errors.add(errorFactory.buildError("ERROR_CODE_23", List.of(placeholder1, placeholder2)));
            }
            return errors;
        }
    }

}
