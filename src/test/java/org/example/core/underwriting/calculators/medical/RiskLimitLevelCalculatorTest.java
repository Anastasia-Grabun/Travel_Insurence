package org.example.core.underwriting.calculators.medical;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.domain.MedicalRiskLimitLevel;
import org.example.core.repositories.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RiskLimitLevelCalculatorTest {

    @Mock
    private MedicalRiskLimitLevelRepository riskLimitLevelRepository;

    @InjectMocks
    private RiskLimitLevelCalculator riskLimitLevelCalculator;

    private PersonDTO personDTO;

    @BeforeEach
    public void setUp(){
        personDTO = new PersonDTO();
    }

    @Test
    void shouldCalculateWhenMedicalRiskLimitLevelIsNotEnabled(){
        personDTO.setMedicalRiskLimitLevel("LEVEL_20000");
        ReflectionTestUtils.setField(riskLimitLevelCalculator, "medicalRiskLimitLevelEnabled", false);

        BigDecimal coefficient = riskLimitLevelCalculator.calculate(personDTO);

        assertEquals(coefficient, BigDecimal.ONE);
        verifyNoInteractions(riskLimitLevelRepository);
    }

    @Test
    void shouldCalculateWhenMedicalRiskLimitLevelIsEnabledWithoutException(){
        personDTO.setMedicalRiskLimitLevel("LEVEL_20000");
        ReflectionTestUtils.setField(riskLimitLevelCalculator, "medicalRiskLimitLevelEnabled", true);

        MedicalRiskLimitLevel level = new MedicalRiskLimitLevel();
        level.setMedicalRiskLimitLevelIc("LEVEL_20000");
        level.setCoefficient(new BigDecimal("1.5"));

        when(riskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_20000"))
                .thenReturn(Optional.of(level));

        BigDecimal coefficient = riskLimitLevelCalculator.calculate(personDTO);

        assertEquals(coefficient, new BigDecimal(1.5));
        verify(riskLimitLevelRepository, times(1)).findByMedicalRiskLimitLevelIc("LEVEL_20000");
    }

    @Test
    void shouldReturnExceptionWhenCalculateMedicalRiskLimitLevelIsEnabled(){
        personDTO.setMedicalRiskLimitLevel("LEVEL_90000");
        ReflectionTestUtils.setField(riskLimitLevelCalculator, "medicalRiskLimitLevelEnabled", true);

        when(riskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_90000"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> riskLimitLevelCalculator.calculate(personDTO)
        );

        assertEquals("Medical risk limit level not found by = LEVEL_90000", exception.getMessage());
        verify(riskLimitLevelRepository, times(1))
                .findByMedicalRiskLimitLevelIc("LEVEL_90000");
    }

}
