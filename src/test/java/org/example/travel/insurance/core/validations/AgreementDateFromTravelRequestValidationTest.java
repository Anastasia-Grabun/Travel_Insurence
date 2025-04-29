package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AgreementDateFromTravelRequestValidationTest {

    private AgreementDateFromValidation validation = new AgreementDateFromValidation();

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(null);

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isPresent());
        Assertions.assertEquals(errors.get().getField(), "agreementDateFrom");
        Assertions.assertEquals(errors.get().getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isEmpty());
    }

}
