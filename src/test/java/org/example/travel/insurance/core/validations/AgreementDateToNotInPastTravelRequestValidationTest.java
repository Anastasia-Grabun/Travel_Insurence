package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.core.DateTimeService;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
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
    private DateTimeService dataTimeService;

    @InjectMocks
    private AgreementDateToNotInPastValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateFromInPast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(parseDate("20.12.2005"));
        when(dataTimeService.getCurrentDateTime()).thenReturn(parseDate("20.10.2025"));

        Optional<ValidationError> errors = validation.execute(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "agreementDateTo");
        assertEquals(errors.get().getMessage(),      "AgreementDateTo must not be in the past!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromInFuture(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(parseDate("20.12.2025"));
        when(dataTimeService.getCurrentDateTime()).thenReturn(parseDate("20.10.2025"));

        Optional<ValidationError> errors = validation.execute(request);

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
