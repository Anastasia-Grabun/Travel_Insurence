package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateToValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_4"))
                .thenReturn(new ValidationError("ERROR_CODE_4", "Field agreementDateTo is empty!"));

        Optional<ValidationError> errors = validation.execute(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_4");
        assertEquals(errors.get().getDescription(),      "Field agreementDateTo is empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());

        Optional<ValidationError> errors = validation.execute(request);

        assertTrue(errors.isEmpty());
    }
}
