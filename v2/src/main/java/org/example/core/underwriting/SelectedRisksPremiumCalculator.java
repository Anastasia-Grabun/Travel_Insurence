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
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    List<RiskDTO> calculatePremiumForAllRisks(AgreementDTO agreement, PersonDTO person) {
        return agreement.getSelectedRisks().stream()
                .map(riskIc -> new RiskDTO(riskIc, calculatePremiumForRisk(riskIc, agreement, person)))
                .toList();
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, AgreementDTO agreement, PersonDTO personDTO) {
        return findRiskPremiumCalculator(riskIc).calculatePremium(agreement, personDTO);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
