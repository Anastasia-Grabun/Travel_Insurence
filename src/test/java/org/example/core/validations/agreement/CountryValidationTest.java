package org.example.core.validations.agreement;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.domain.ClassifierValue;
import org.example.core.repositories.ClassifierValueRepository;
import org.example.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
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
       AgreementDTO request = mock(AgreementDTO.class);

        when(request.getCountry()).thenReturn(null);

        assertTrue(countryValidation.validate(request).isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldValidateWithErrors() {
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getCountry()).thenReturn("Korea");

        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "Korea"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_15"), anyList())).thenReturn(error);

        assertEquals(Optional.of(error), countryValidation.validate(request));
    }

    @Test
    public void shouldValidateWithoutErrors(){
        AgreementDTO request = mock(AgreementDTO.class);

        when(request.getCountry()).thenReturn("Japan");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "Japan"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));

        assertTrue(countryValidation.validate(request).isEmpty());
    }

}
