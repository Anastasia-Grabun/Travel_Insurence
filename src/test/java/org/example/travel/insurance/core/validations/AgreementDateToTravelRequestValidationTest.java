package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AgreementDateToTravelRequestValidationTest {

    private AgreementDateToValidation validation = new AgreementDateToValidation();

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(null);

        Optional<ValidationError> errors = validation.execute(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "agreementDateTo");
        assertEquals(errors.get().getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());

        Optional<ValidationError> errors = validation.execute(request);

        assertTrue(errors.isEmpty());
    }
}
