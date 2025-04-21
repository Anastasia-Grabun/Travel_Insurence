package org.example.travel.insurance.core;

import org.springframework.stereotype.Component;
import org.example.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.example.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculateDurationInDays(request));

        return response;
    }

    private BigDecimal calculateDurationInDays(TravelCalculatePremiumRequest request){
        long differenceInMilliseconds = request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime();
        return valueOf(differenceInMilliseconds / (1000 * 60 * 60 * 24));
    }

}
