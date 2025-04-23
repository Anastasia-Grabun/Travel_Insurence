package org.example.travel.insurance.core;

import org.example.travel.insurance.rest.TravelCalculatePremiumRequest;
import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

class DateTimeService {
    public BigDecimal calculateDurationInDays(TravelCalculatePremiumRequest request){
        long differenceInMilliseconds = request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime();
        return valueOf(differenceInMilliseconds / (1000 * 60 * 60 * 24));
    }
}
