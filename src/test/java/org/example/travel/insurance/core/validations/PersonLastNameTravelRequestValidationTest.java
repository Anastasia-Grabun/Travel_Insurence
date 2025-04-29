package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonLastNameTravelRequestValidationTest {

    private PersonLastNameValidation validation = new PersonLastNameValidation();

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIs(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn("Komatsu");

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn(null);

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertFalse(errors.isEmpty());
        Assertions.assertEquals(errors.get().getField(), "personLastName");
        Assertions.assertEquals(errors.get().getMessage(),      "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn("");

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isPresent());
        Assertions.assertEquals(errors.get().getField(), "personLastName");
        Assertions.assertEquals(errors.get().getMessage(), "Must not be empty!");
    }

}
