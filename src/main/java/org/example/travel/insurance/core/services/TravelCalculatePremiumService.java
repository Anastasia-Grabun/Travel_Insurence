package org.example.travel.insurance.core.services;


import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request);

}
