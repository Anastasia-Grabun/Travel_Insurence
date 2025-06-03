package org.example.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import org.example.travel.insurance.dto.RiskPremium;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    public List<RiskPremium> calculatePremiumForAllRisks(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = request.getSelected_risks().stream()
                .map(riskIc -> {
                    return new RiskPremium(riskIc, calculatePremiumForRisk(riskIc, request));
                })
                .toList();

        return riskPremiums;
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
