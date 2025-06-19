package org.example.core.validations;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelAgreementFieldValidatorTest {

    @Test
    public void shouldNotReturnErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement)).thenReturn(List.of());
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement)).thenReturn(List.of());

        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        var validator = new TravelAgreementFieldValidator(agreementValidations);

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSingleAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO()));

        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        var validator = new TravelAgreementFieldValidator(agreementValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnListAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement)).thenReturn(List.of(new ValidationErrorDTO()));

        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        var validator = new TravelAgreementFieldValidator(agreementValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(errors.size(), 2);
    }

}