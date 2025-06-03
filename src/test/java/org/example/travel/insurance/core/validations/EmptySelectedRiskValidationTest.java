package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmptySelectedRiskValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptySelectedRisksValidation validation;


    @Test
    public void shouldNotReturnErrorWhenSelectedRiskAreChosen(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getSelected_risks()).thenReturn(Collections.singletonList("TRAVEL_MEDICAL"));

        Optional<ValidationError> errors = validation.validate(request);

        assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksAreNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getSelected_risks()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationError("ERROR_CODE_8", "Array Selected_risks must not be empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().errorCode(), "ERROR_CODE_8");
        assertEquals(errors.get().description(),"Array Selected_risks must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getSelected_risks()).thenReturn(Collections.EMPTY_LIST);
        when(errorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationError("ERROR_CODE_8", "Array Selected_risks must not be empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().errorCode(), "ERROR_CODE_8");
        assertEquals(errors.get().description(),"Array Selected_risks must not be empty!");
    }

}
