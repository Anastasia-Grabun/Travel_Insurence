package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelectedRiskRequestValidationTest {

    private SelectedRisksValidation validation = new SelectedRisksValidation();


    @Test
    public void shouldNotReturnErrorWhenSelectedRiskAreChosen(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getSelected_risks()).thenReturn(Collections.singletonList("TRAVEL_MEDICAL"));

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksAreNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getSelected_risks()).thenReturn(null);

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertFalse(errors.isEmpty());
        Assertions.assertEquals(errors.get().getField(), "Selected_risks");
        Assertions.assertEquals(errors.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getSelected_risks()).thenReturn(Collections.EMPTY_LIST);

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isPresent());
        Assertions.assertEquals(errors.get().getField(), "Selected_risks");
        Assertions.assertEquals(errors.get().getMessage(), "Must not be empty!");
    }
}
