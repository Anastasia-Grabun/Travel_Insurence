package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());

        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(requestValidator, "travelValidations", travelValidations);
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

        List<TravelRequestValidation> travelValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(requestValidator, "singleValidations", travelValidations);
        List<ValidationError> errors = requestValidator.validate(request);

        assertEquals(2, errors.size());
    }

}
