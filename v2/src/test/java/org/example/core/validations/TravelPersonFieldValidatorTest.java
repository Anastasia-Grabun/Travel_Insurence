package org.example.core.validations;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelPersonFieldValidatorTest {

    @Test
    public void shouldNotReturnErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(agreement, person)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement, person)).thenReturn(List.of());
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(agreement, person)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement, person)).thenReturn(List.of());

        var validator = new TravelPersonFieldValidator(List.of(validation1, validation2));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(agreement, person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(agreement, person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        var validator = new TravelPersonFieldValidator(List.of(validation1, validation2));
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnListPersonErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(agreement, person)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement, person)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(agreement, person)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement, person)).thenReturn(List.of(new ValidationErrorDTO()));
        var validator = new TravelPersonFieldValidator(List.of(validation1, validation2));
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 2);
    }

}