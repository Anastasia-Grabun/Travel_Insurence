package org.example.travel.insurance.core.underwriting;

import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request);

    String getRiskIc();

}
