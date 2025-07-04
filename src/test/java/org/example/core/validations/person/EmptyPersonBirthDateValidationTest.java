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
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyPersonBirthDateValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyPersonBirthDateValidation validation;

    @Test
    public void shouldReturnNoErrorWhenPersonBirthDateIsPresent() {
        PersonDTO request = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(request.getPersonBirthDate()).thenReturn(new Date());

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreementDTO, request);
        assertTrue(errorOpt.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPersonBirthDateIsNull() {
        PersonDTO request = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(request.getPersonBirthDate()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_11"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_11", "Person Birth Date must be provided when TRAVEL_MEDICAL is selected"));

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreementDTO, request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_11",
                errorOpt.get().getErrorCode());
        assertEquals("Person Birth Date must be provided when TRAVEL_MEDICAL is selected",
                errorOpt.get().getDescription());
    }

}