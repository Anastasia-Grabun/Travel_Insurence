package org.example.core.services;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import org.example.core.api.dto.RiskDTO;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Collection;

@Component
class AgreementTotalPremiumCalculator {

    BigDecimal calculate(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
