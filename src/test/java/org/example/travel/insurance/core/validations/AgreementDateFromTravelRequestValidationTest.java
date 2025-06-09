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
public class AgreementDateFromTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateFromValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        when(request.getAgreementDateFrom()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_3"))
                .thenReturn(new ValidationError("ERROR_CODE_3", "Field agreementDateFrom is empty!"));

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().errorCode(), "ERROR_CODE_3");
        assertEquals(errors.get().description(),      "Field agreementDateFrom is empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> errors = validation.validate(request);

        assertTrue(errors.isEmpty());
    }

}
