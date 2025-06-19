package org.example.core.validations.agreement;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateFromLessDateToTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private DateFromLessDateToValidation validation;

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToAfterAgreementDateFrom(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateFrom()).thenReturn(convertToDate("2010-10-11"));
        when(request.getAgreementDateTo()).thenReturn(convertToDate("2011-12-20"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToBeforeAgreementDateFrom(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(errorFactory.buildError("ERROR_CODE_7"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_7", "Field agreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_7");
        assertEquals(errors.get().getDescription(), "Field agreementDateTo must be after AgreementDateFrom!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToEqualsAgreementDateFrom(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateFrom()).thenReturn(convertToDate("2024-10-10"));
        when(request.getAgreementDateTo()).thenReturn(convertToDate("2024-10-10"));
        when(errorFactory.buildError("ERROR_CODE_7"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_7", "Field agreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_7");
        assertEquals(errors.get().getDescription(), "Field agreementDateTo must be after AgreementDateFrom!");
    }

    private Date convertToDate(String date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
