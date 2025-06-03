package org.example.travel.insurance.core.underwriting.calculators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.domain.CountryDefaultDayRate;
import org.example.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.example.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.example.travel.insurance.core.util.DateTimeUtil;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelMedicalPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeUtil dateTimeUtil;
    private final CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysCount = calculateDayCount(request);
        var countryDefaultRate = findCountryDefaultDayRate(request);

        return countryDefaultRate.multiply(daysCount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateDayCount(TravelCalculatePremiumRequest request){
        var days = dateTimeUtil.calculateDurationInDays
                (request.getAgreementDateFrom(), request.getAgreementDateTo());

        return new BigDecimal(days);
    }

    private BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequest request) {
        return countryDefaultDayRateRepository.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + request.getCountry()));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
