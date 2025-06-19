package org.example.core.underwriting;

import org.example.core.api.dto.RiskDTO;
import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskDTO> risks
) {}

