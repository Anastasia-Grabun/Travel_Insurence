package org.example.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.util.DateTimeUtil;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;

    BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        var daysBetween = dateTimeUtil.calculateDurationInDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

}
