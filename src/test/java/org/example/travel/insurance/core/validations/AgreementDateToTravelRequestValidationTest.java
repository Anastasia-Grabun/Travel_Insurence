package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        when(request.getAgreementDateTo()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_4"))
                .thenReturn(new ValidationError("ERROR_CODE_4", "Field agreementDateTo is empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().errorCode(), "ERROR_CODE_4");
        assertEquals(errors.get().description(),      "Field agreementDateTo is empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isEmpty());
    }
}
