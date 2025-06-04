package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorImplTest {

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);

        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation2.validate(request)).thenReturn(Optional.empty());

        List<TravelRequestValidation> singleValidations = List.of(validation1, validation2);
        List<TravelRequestListValidation> listValidations = List.of(); // no list validations for this test

        TravelCalculatePremiumRequestValidatorImpl requestValidator =
                new TravelCalculatePremiumRequestValidatorImpl(singleValidations, listValidations);

        List<ValidationError> errors = requestValidator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);

        when(validation1.validate(request))
                .thenReturn(Optional.of(new ValidationError("ERR1", "Invalid destination")));
        when(validation2.validate(request))
                .thenReturn(Optional.of(new ValidationError("ERR2", "Missing start date")));

        List<TravelRequestValidation> singleValidations = List.of(validation1, validation2);
        List<TravelRequestListValidation> listValidations = List.of(); // no list validations for this test

        TravelCalculatePremiumRequestValidatorImpl requestValidator =
                new TravelCalculatePremiumRequestValidatorImpl(singleValidations, listValidations);

        List<ValidationError> errors = requestValidator.validate(request);

        assertEquals(2, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e.errorCode().equals("ERR1")));
        assertTrue(errors.stream().anyMatch(e -> e.errorCode().equals("ERR2")));
    }
}