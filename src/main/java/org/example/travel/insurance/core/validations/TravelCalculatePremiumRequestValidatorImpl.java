package org.example.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements
        TravelCalculatePremiumRequestValidator{

    private final List<TravelRequestValidation> singleValidations;
    private final List<TravelRequestListValidation> listValidations;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleErrors = collectSingleErrors(request);
        List<ValidationError> listErrors = collectListErrors(request);

        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationError> collectSingleErrors(TravelCalculatePremiumRequest request) {
        return singleValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectListErrors(TravelCalculatePremiumRequest request) {
        return listValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationError> concatenateLists(List<ValidationError> singleErrors,
                                                   List<ValidationError> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
