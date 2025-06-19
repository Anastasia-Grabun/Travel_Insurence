package org.example.core.validations.agreement;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.ValidationErrorFactory;
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
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateFrom()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_3"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_3", "Field agreementDateFrom is empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_3");
        assertEquals(errors.get().getDescription(),      "Field agreementDateFrom is empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isEmpty());
    }

}
