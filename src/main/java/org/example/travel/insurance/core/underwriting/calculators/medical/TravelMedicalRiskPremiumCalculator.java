package org.example.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DayCountCalculator dayCountCalculator;
    private final CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    private final AgeCoefficientCalculator ageCoefficientCalculator;
    private final RiskLimitLevelCalculator riskLimitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        var daysCount = dayCountCalculator.calculate(request);
        var countryDefaultRate = countryDefaultDayRateCalculator.calculate(request);
        var ageCoefficient = ageCoefficientCalculator.calculate(request);
        var medicalRiskLimitLevel = riskLimitLevelCalculator.calculate(request);

        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .multiply(medicalRiskLimitLevel)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}

