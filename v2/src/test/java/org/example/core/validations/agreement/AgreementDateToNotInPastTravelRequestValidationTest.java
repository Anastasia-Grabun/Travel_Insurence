package org.example.core.validations.agreement;

import org.example.core.api.dto.AgreementDTO;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToNotInPastTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @Mock
    private DateTimeUtil dataTimeService;

    @InjectMocks
    private AgreementDateToNotInPastValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateToInPast(){
            AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateTo()).thenReturn(parseDate("20.12.2005"));
        when(dataTimeService.getCurrentDateTime()).thenReturn(parseDate("20.10.2025"));
        when(errorFactory.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_6", "Field agreementDateTo must not be in the past!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_6");
        assertEquals(errors.get().getDescription(),"Field agreementDateTo must not be in the past!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromInFuture(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateTo()).thenReturn(parseDate("20.12.2025"));
        when(dataTimeService.getCurrentDateTime()).thenReturn(parseDate("20.10.2025"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isEmpty());
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
