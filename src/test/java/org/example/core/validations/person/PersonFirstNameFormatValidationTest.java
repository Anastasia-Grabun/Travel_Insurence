package org.example.core.validations.person;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonFirstNameFormatValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonFirstNameFormatValidation validation;

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn(null);

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);

        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn("");

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);

        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsRightFormatted() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn("John Doe-Smith");

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);

        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNotRightFormatted() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn("John Doe-Smith 12");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_22"), anyList())).thenReturn(validationError);

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);

        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

}
