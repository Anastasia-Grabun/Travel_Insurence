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
public class PersonFirstNameTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyPersonFirstNameValidation validation;

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIs(){
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonFirstName()).thenReturn("Nana");

        Optional<ValidationErrorDTO> errors = validation.validate(agreementDTO, personDTO);

        assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty(){
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonFirstName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_1", "Field personFirstName is empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(agreementDTO, personDTO);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(),"ERROR_CODE_1");
        assertEquals(errors.get().getDescription(), "Field personFirstName is empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonFirstName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_1",
                        "Field personFirstName is empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(agreementDTO, personDTO);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getErrorCode(),"ERROR_CODE_1");
        assertEquals(errors.get().getDescription(), "Field personFirstName is empty!");
    }

}
