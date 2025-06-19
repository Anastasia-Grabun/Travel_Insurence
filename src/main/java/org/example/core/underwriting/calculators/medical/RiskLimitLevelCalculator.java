package org.example.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.PersonDTO;
import org.example.core.domain.MedicalRiskLimitLevel;
import org.example.core.repositories.MedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RiskLimitLevelCalculator {

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    private final MedicalRiskLimitLevelRepository riskLimitLevelRepository;

    BigDecimal calculate(PersonDTO personDTO){
        return medicalRiskLimitLevelEnabled ?
                getCoefficient(personDTO) : getDefaultValue();
    }

    private BigDecimal getCoefficient(PersonDTO personDTO){
        return riskLimitLevelRepository.findByMedicalRiskLimitLevelIc(personDTO.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Medical risk limit level not found by = " + personDTO.getMedicalRiskLimitLevel()));

    }

    private BigDecimal getDefaultValue(){
        return BigDecimal.ONE;
    }

}

