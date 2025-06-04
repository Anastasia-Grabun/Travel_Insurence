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
        log.info("Single errors collected: {}", singleErrors);

        List<ValidationError> listErrors = collectListErrors(request);
        log.info("List errors collected: {}", listErrors);

        List<ValidationError> allErrors = concatenateLists(singleErrors, listErrors);
        log.info("All validation errors combined: {}", allErrors);

        return allErrors;
    }

    private List<ValidationError> collectSingleErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = singleValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        log.info("Single errors collected: {}", errors);

        return errors;
    }

    private List<ValidationError> collectListErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = listValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        log.info("List errors collected: {}", errors);

        return errors;
    }

    private List<ValidationError> concatenateLists(List<ValidationError> singleErrors,
                                                   List<ValidationError> listErrors) {
        List<ValidationError> allErrors = Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());

        log.info("All validation errors combined: {}", allErrors);

        return allErrors;
    }

}
