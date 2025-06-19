package org.example.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.underwriting.TravelPremiumCalculationResult;
import org.example.core.underwriting.TravelPremiumUnderwriting;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementPersonsPremiumCalculator {

    private final TravelPremiumUnderwriting premiumUnderwriting;

    void calculateRiskPremiums(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            person.setRisks(calculationResult.risks());
        });
    }

}
