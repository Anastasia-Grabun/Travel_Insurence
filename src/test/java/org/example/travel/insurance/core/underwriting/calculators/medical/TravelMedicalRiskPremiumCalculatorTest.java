package org.example.travel.insurance.core.underwriting.calculators.medical;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
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

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
    }

    @Test
    void shouldCalculatePremiumCorrectly() {
        BigDecimal daysCount = BigDecimal.valueOf(10);
        BigDecimal countryDefaultRate = BigDecimal.valueOf(20);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.2);
        BigDecimal riskCoefficient = BigDecimal.valueOf(1.5);

        when(dayCountCalculator.calculate(request)).thenReturn(daysCount);
        when(countryDefaultDayRateCalculator.calculate(request)).thenReturn(countryDefaultRate);
        when(ageCoefficientCalculator.calculate(request)).thenReturn(ageCoefficient);
        when(riskCalculator.calculate(request)).thenReturn(riskCoefficient);

        BigDecimal expectedPremium = countryDefaultRate.multiply(daysCount)
                .multiply(ageCoefficient).multiply(riskCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = calculator.calculatePremium(request);

        assertEquals(expectedPremium, result);
    }

}