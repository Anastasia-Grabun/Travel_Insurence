package org.example.travel.insurance.core;


import org.example.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.example.travel.insurance.rest.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
