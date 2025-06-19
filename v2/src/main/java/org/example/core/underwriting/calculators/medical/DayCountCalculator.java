package org.example.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.util.DateTimeUtil;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;

    BigDecimal calculate(AgreementDTO agreementDTO) {
        var daysBetween = dateTimeUtil.getDaysBetween(agreementDTO.getAgreementDateFrom(), agreementDTO.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

}
