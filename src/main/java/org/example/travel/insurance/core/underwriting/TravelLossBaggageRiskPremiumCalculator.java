package org.example.travel.insurance.core.underwriting;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
class TravelLossBaggageRiskPremiumCalculator implements TravelRiskPremiumCalculator{

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_LOSS_BAGGAGE";
    }

}
