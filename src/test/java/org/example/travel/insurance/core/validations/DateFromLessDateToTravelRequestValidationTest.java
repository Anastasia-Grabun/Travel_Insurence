package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateFromLessDateToTravelRequestValidationTest {

    private DateFromLessDateToValidation validation = new DateFromLessDateToValidation();

    @Test
    public void shouldNotReturnErrorWhenAgreementDateToAfterAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(convertToDate("2010-10-11"));
        when(request.getAgreementDateTo()).thenReturn(convertToDate("2011-12-20"));

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToBeforeAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());
        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isPresent());
        Assertions.assertEquals(errors.get().getField(), "agreementDateTo");
        Assertions.assertEquals(errors.get().getMessage(), "AgreementDateTo must be after AgreementDateFrom!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToEqualsAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(convertToDate("2024-10-10"));
        when(request.getAgreementDateTo()).thenReturn(convertToDate("2024-10-10"));

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isPresent());
        Assertions.assertEquals( errors.get().getField(), "agreementDateTo");
        Assertions.assertEquals(errors.get().getMessage(), "AgreementDateTo must be after AgreementDateFrom!");
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
