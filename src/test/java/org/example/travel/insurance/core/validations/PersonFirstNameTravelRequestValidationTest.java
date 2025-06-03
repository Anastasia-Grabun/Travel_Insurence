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
public class PersonFirstNameTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonFirstNameValidation validation;

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIs(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn("Nana");

        Optional<ValidationError> errors = validation.validate(request);

        assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().errorCode(),"ERROR_CODE_1");
        assertEquals(errors.get().description(), "Field personFirstName is empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().errorCode(),"ERROR_CODE_1");
        assertEquals(errors.get().description(), "Field personFirstName is empty!");
    }

}
