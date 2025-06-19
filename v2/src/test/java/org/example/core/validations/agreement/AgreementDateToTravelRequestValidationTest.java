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
public class AgreementDateToTravelRequestValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private AgreementDateToValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateTo()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_4"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_4", "Field agreementDateTo is empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_4");
        assertEquals(errors.get().getDescription(),      "Field agreementDateTo is empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAgreementDateFromNotNull(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getAgreementDateTo()).thenReturn(new Date());

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isEmpty());
    }

}
