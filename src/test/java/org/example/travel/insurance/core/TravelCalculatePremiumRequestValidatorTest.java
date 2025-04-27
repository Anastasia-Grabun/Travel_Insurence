package org.example.travel.insurance.core;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator travelValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldNotReturnErrorWhenAllFieldsArePresent(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn("Neely");
        when(request.getPersonLastName()).thenReturn("Green");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());

        List<ValidationError> errors = travelValidator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenRequestIsNull(){
        List<ValidationError> errors = travelValidator.validate(null);

        assertEquals(1, errors.size());
        assertEquals(errors.getFirst().getField(), "request");
        assertEquals(errors.getFirst().getMessage(), "Request must not be null!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn(null);

        List<ValidationError> errors = travelValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.getFirst().getField(), "personFirstName");
        assertEquals(errors.getFirst().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn("");

        List<ValidationError> errors = travelValidator.validate(request);

        assertNull(errors);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }


    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn(null);

        List<ValidationError> errors = travelValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn("");

        List<ValidationError> errors = travelValidator.validate(request);

        assertNull(errors);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(null);

        List<ValidationError> errors = travelValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(2).getField(), "agreementDateFrom");
        assertEquals(errors.get(2).getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(null);

        List<ValidationError> errors = travelValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 4);
        assertEquals(errors.get(3).getField(), "agreementDateTo");
        assertEquals(errors.get(3).getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToBeforeAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());
        when(request.getAgreementDateFrom()).thenReturn(new Date());

        List<ValidationError> errors = travelValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 3);
        assertEquals( errors.get(2).getField(), "agreementDateTo");
        assertEquals(errors.get(2).getMessage(), "AgreementDateTo must be after AgreementDateFrom!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToEqualsAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(convertToDate("2024-10-10"));
        when(request.getAgreementDateTo()).thenReturn(convertToDate("2024-10-10"));

        List<ValidationError> errors = travelValidator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 3);
        assertEquals( errors.get(2).getField(), "agreementDateTo");
        assertEquals(errors.get(2).getMessage(), "AgreementDateTo must be after AgreementDateFrom!");
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
