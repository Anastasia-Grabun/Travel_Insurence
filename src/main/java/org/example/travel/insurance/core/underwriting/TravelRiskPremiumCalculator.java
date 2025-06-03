package org.example.travel.insurance.core.underwriting;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);

    String getRiskIc();

}
