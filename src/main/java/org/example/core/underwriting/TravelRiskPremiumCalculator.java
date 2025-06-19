package org.example.core.underwriting;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;
import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();

}
