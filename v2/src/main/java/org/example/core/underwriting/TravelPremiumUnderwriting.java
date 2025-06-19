package org.example.core.underwriting;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.PersonDTO;

public interface TravelPremiumUnderwriting {
    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);

}
