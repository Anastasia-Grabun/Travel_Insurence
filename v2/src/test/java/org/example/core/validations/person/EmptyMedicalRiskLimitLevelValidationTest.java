package org.example.core.validations.person;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyMedicalRiskLimitLevelValidationTest {

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyMedicalRiskLimitLevelValidation validation;

    private PersonDTO personDTO;

    private AgreementDTO agreementDTO;

    @BeforeEach
    void setUp() {
        personDTO = new PersonDTO();
        agreementDTO = new AgreementDTO();
    }

    @Test
    void shouldReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndNullOrBlank() {
        personDTO.setMedicalRiskLimitLevel(null);
        ValidationErrorDTO expectedError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(expectedError);

        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);

        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO, personDTO);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndIsNotBlank() {
        personDTO.setMedicalRiskLimitLevel("LEVEL_10000");
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO, personDTO);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelNotEnabledAndIsBlank() {
        personDTO.setMedicalRiskLimitLevel("");
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationErrorDTO> result = validation.validate(agreementDTO, personDTO);
        assertTrue(result.isEmpty());
    }

}
