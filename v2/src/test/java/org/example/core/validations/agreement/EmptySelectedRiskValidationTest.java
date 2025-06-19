package org.example.core.validations.agreement;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.ValidationErrorFactory;
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
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getSelectedRisks()).thenReturn(Collections.singletonList("TRAVEL_MEDICAL"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertFalse(errors.isPresent());
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksAreNull(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getSelectedRisks()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8", "Array Selected_risks must not be empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(errors.get().getDescription(),"Array Selected_risks must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getSelectedRisks()).thenReturn(Collections.EMPTY_LIST);
        when(errorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8", "Array Selected_risks must not be empty!"));

        Optional<ValidationErrorDTO> errors = validation.validate(request);

        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(errors.get().getDescription(),"Array Selected_risks must not be empty!");
    }

}
