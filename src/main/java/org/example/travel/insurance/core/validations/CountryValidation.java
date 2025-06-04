package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.repositories.ClassifierValueRepository;
import org.example.travel.insurance.core.util.Placeholder;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CountryValidation implements TravelRequestValidation{

    private final ValidationErrorFactory errorFactory;
    private final ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        String country = request.getCountry();
        return (country == null || country.isEmpty()) ? Optional.empty() : validateCountry(country);
    }

    private Optional<ValidationError> validateCountry(String countryIc){
        return !existInDatabase(countryIc)
                ? Optional.of(buildValidationError(countryIc))
                : Optional.empty();
    }

    private ValidationError buildValidationError(String countryIc){
        Placeholder placeholder = new Placeholder("NOT_EXISTING_COUNTRY", countryIc);
        return errorFactory.buildError("ERROR_CODE_9", List.of(placeholder));
    }

    private boolean existInDatabase(String countryIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent();
    }

}
