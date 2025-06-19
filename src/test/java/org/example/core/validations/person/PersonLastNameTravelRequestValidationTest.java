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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonLastNameTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIs(){
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonLastName()).thenReturn("Komatsu");

        Optional<ValidationErrorDTO> errors = validation.validate(agreementDTO, personDTO);

        assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonLastName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(agreementDTO, personDTO);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_2");
        assertEquals(errors.get().getDescription(), "Field personLastName is empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonLastName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(agreementDTO, personDTO);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_2");
        assertEquals(errors.get().getDescription(), "Field personLastName is empty!");
    }

}
