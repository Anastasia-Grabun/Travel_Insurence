package org.example.core.validations.person;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.util.DateTimeUtil;
import org.example.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonBirthDateInThePastValidationTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonBirthDateInThePastValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonBirthDateInTheFuture() {
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonBirthDate()).thenReturn(createDate("01.01.2030"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);

        when(errorFactory.buildError("ERROR_CODE_12")).thenReturn(validationError);

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreementDTO, personDTO);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonBirthDateDateInThePast() {
        PersonDTO personDTO = mock(PersonDTO.class);
        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        when(personDTO.getPersonBirthDate()).thenReturn(createDate("01.01.2020"));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));

        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreementDTO, personDTO);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}