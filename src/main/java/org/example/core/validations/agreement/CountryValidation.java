package org.example.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.repositories.ClassifierValueRepository;
import org.example.core.util.Placeholder;
import org.example.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CountryValidation extends TravelAgreementFieldValidationImpl {

    private final ClassifierValueRepository classifierValueRepository;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (isCountryNotBlank(agreement))
                && !existInDatabase(agreement.getCountry())
                ? Optional.of(buildValidationError(agreement.getCountry()))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(String country) {
        Placeholder placeholder = new Placeholder("NOT_SUPPORTED_COUNTRY", country);
        return errorFactory.buildError("ERROR_CODE_15", List.of(placeholder));
    }


    private boolean isCountryNotBlank(AgreementDTO agreementDTO) {
        return agreementDTO.getCountry() != null && !agreementDTO.getCountry().isBlank();
    }

    private boolean existInDatabase(String countryIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("COUNTRY", countryIc).isPresent();
    }

}