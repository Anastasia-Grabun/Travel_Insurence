package org.example.travel.insurance.core.underwriting;

import org.example.travel.insurance.core.underwriting.calculators.TravelPremiumCalculationResult;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request);

}
