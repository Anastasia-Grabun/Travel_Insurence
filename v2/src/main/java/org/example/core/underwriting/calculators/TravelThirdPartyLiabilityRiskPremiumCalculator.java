package org.example.core.underwriting.calculators;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
class TravelThirdPartyLiabilityRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_THIRD_PARTY_LIABILITY";
    }

}
