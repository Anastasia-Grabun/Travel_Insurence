package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonLastNameTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIs(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn("Komatsu");

        Optional<ValidationError> errors = validation.validate(request);

        assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().errorCode(), "ERROR_CODE_2");
        assertEquals(errors.get().description(), "Field personLastName is empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonLastName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().errorCode(), "ERROR_CODE_2");
        assertEquals(errors.get().description(), "Field personLastName is empty!");
    }

}
