package org.example.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.RiskDTO;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person) {
        List<RiskDTO> riskPremiums = calculateSelectedRisksPremium(agreement, person);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);

        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskDTO> calculateSelectedRisksPremium(AgreementDTO agreementDTO, PersonDTO personDTO) {
        return selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreementDTO, personDTO);
    }

    private static BigDecimal calculateTotalPremium(List<RiskDTO> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
