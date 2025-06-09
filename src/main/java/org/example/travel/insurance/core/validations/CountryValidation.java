package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.repositories.ClassifierValueRepository;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CountryValidation implements TravelRequestValidation {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (isCountryNotBlank(request))
                && !existInDatabase(request.getCountry())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }

    private boolean isCountryNotBlank(TravelCalculatePremiumRequestV1 request) {
        return request.getCountry() != null && !request.getCountry().isBlank();
    }

    private boolean existInDatabase(String countryIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent();
    }

}