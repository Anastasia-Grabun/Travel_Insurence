package org.example.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.domain.CountryDefaultDayRate;
import org.example.core.repositories.CountryDefaultDayRateRepository;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CountryDefaultDayRateCalculator {

    private final CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal calculate(AgreementDTO agreementDTO) {
        return countryDefaultDayRateRepository.findByCountryIc(agreementDTO.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + agreementDTO.getCountry()));
    }

}
