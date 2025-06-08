package org.example.travel.insurance.core.validations;

import org.example.travel.insurance.core.domain.ClassifierValue;
import org.example.travel.insurance.core.repositories.ClassifierValueRepository;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @InjectMocks
    private CountryValidation countryValidation;

    @Test
    public void shouldNotValidateWhenCountryNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getCountry()).thenReturn(null);

        assertTrue(countryValidation.validate(request).isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldValidateWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getCountry()).thenReturn("Korea");

        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "Korea"))
                .thenReturn(Optional.empty());

        ValidationError error = mock(ValidationError.class);
        when(errorFactory.buildError(("ERROR_CODE_15"))).thenReturn(error);

        assertEquals(Optional.of(error), countryValidation.validate(request));
    }

    @Test
    public void shouldValidateWithoutErrors(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getCountry()).thenReturn("Japan");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "Japan"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));

        assertTrue(countryValidation.validate(request).isEmpty());
    }

}
