package org.example.core.underwriting.calculators.medical;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock
    private DayCountCalculator dayCountCalculator;

    @Mock
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;

    @Mock
    private AgeCoefficientCalculator ageCoefficientCalculator;

    @Mock
    private RiskLimitLevelCalculator riskCalculator;

    @InjectMocks
    private TravelMedicalRiskPremiumCalculator calculator;

    private AgreementDTO agreementDTO;
    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        agreementDTO = new AgreementDTO();
        personDTO = new PersonDTO();
    }

    @Test
    void shouldCalculatePremiumCorrectly() {
        BigDecimal daysCount = BigDecimal.valueOf(10);
        BigDecimal countryDefaultRate = BigDecimal.valueOf(20);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.2);
        BigDecimal riskCoefficient = BigDecimal.valueOf(1.5);

        when(dayCountCalculator.calculate(agreementDTO)).thenReturn(daysCount);
        when(countryDefaultDayRateCalculator.calculate(agreementDTO)).thenReturn(countryDefaultRate);
        when(ageCoefficientCalculator.calculate(personDTO)).thenReturn(ageCoefficient);
        when(riskCalculator.calculate(personDTO)).thenReturn(riskCoefficient);

        BigDecimal expectedPremium = countryDefaultRate.multiply(daysCount)
                .multiply(ageCoefficient).multiply(riskCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = calculator.calculatePremium(agreementDTO, personDTO);

        assertEquals(expectedPremium, result);
    }

}