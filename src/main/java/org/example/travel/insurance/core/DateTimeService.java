package org.example.travel.insurance.core;

import org.example.travel.insurance.rest.TravelCalculatePremiumRequest;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.valueOf;

class DateTimeService {
    long calculateDurationInDays(TravelCalculatePremiumRequest request){
        long differenceInMilliseconds = request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime();
        return TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
    }
}
