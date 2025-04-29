package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonFirstNameTravelRequestValidationTest {

    private PersonFirstNameValidation validation = new PersonFirstNameValidation();

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIs(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn("Nana");

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn("");

        Optional<ValidationError> errors = validation.execute(request);

        Assertions.assertTrue(errors.isPresent());
        Assertions.assertEquals(errors.get().getField(),"personFirstName");
        Assertions.assertEquals(errors.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn(null);

        Optional<ValidationError> errors = validation.execute(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getField(), "personFirstName");
        assertEquals(errors.get().getMessage(), "Must not be empty!");
    }

}
