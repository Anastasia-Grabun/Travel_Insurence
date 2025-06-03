package org.example.travel.insurance.core.underwriting.calculators;

import org.example.travel.insurance.dto.RiskPremium;
import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskPremium> riskPremiums
) {}

