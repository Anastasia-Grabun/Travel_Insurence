package org.example.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.example.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RiskLimitLevelCalculator {

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final MedicalRiskLimitLevelRepository riskLimitLevelRepository;

    BigDecimal calculate(TravelCalculatePremiumRequestV1 request){
        return medicalRiskLimitLevelEnabled ?
                getCoefficient(request) : getDefaultValue();
    }

    private BigDecimal getCoefficient(TravelCalculatePremiumRequestV1 request){
        return riskLimitLevelRepository.findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Medical risk limit level not found by = " + request.getMedicalRiskLimitLevel()));

    }

    private BigDecimal getDefaultValue(){
        return BigDecimal.ONE;
    }

}

