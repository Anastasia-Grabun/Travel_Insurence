package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class SelectedRisksValidation implements TravelRequestValidation{

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelected_risks() == null || request.getSelected_risks().isEmpty())
                ? Optional.of(new ValidationError("Selected_risks", "Must not be empty!"))
                : Optional.empty();
    }

}
