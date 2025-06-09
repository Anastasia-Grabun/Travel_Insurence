package org.example.travel.insurance.core.underwriting;

import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequestV1 request);

}
