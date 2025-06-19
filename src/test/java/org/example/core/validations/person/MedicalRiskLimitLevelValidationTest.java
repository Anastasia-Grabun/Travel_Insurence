package org.example.core.validations.person;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.domain.ClassifierValue;
import org.example.core.repositories.ClassifierValueRepository;
import org.example.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalRiskLimitLevelValidationTest {

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private MedicalRiskLimitLevelValidation validation;

    @Test
    public void shouldNotReturnErrorWhenMedicalRiskLimitLevelNotEnabled() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);
        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenNotContainTravelMedicalRisk() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);

        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenMedicalRiskLimitLevelIsNull() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        when(personDTO.getMedicalRiskLimitLevel()).thenReturn(null);
        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);
        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenMedicalRiskLimitLevelIsBlank() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("");
        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);
        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenMedicalRiskLimitLevelExistInDb() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        ClassifierValue classifierValue = mock(ClassifierValue.class);
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.of(classifierValue));

        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);

        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldReturnError() {
        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.empty());
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_14"), anyList())).thenReturn(validationError);

        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreementDTO, personDTO);

        assertTrue(validationErrorOpt.isPresent());
        assertSame(validationError, validationErrorOpt.get());
    }

}