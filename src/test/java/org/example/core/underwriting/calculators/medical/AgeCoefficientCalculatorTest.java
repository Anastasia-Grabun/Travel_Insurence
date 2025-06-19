package org.example.core.underwriting.calculators.medical;

import org.example.core.api.dto.PersonDTO;
import org.example.core.domain.AgeCoefficient;
import org.example.core.repositories.AgeCoefficientRepository;
import org.example.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgeCoefficientCalculatorTest {

    private DateTimeUtil dateTimeUtil;
    private AgeCoefficientRepository ageCoefficientRepository;

    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        dateTimeUtil = mock(DateTimeUtil.class);
        ageCoefficientRepository = mock(AgeCoefficientRepository.class);

        personDTO = new PersonDTO();
        personDTO.setPersonBirthDate(Date.from(LocalDate.of(1990, 1, 1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    void shouldReturnOneWhenDisabled() {
        var calculator = new AgeCoefficientCalculator(false, dateTimeUtil, ageCoefficientRepository);
        BigDecimal result = calculator.calculate(personDTO);
        assertEquals(BigDecimal.ONE, result);
    }

    @Test
    void shouldFindCoefficientWhenAgeCoefficientExists() {
        var calculator = new AgeCoefficientCalculator(true, dateTimeUtil, ageCoefficientRepository);
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;
        BigDecimal expectedCoefficient = BigDecimal.valueOf(1.2);

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        AgeCoefficient ageCoefficient = mock(AgeCoefficient.class);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedCoefficient);
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.of(ageCoefficient));

        BigDecimal result = calculator.calculate(personDTO);

        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenAgeCoefficientNotFound() {
        var calculator = new AgeCoefficientCalculator(true, dateTimeUtil, ageCoefficientRepository);
        LocalDate currentDate = LocalDate.of(2023, 3, 27);
        int age = 33;

        when(dateTimeUtil.getCurrentDateTime()).thenReturn(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        when(ageCoefficientRepository.findCoefficient(age)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(personDTO));

        assertEquals("Age coefficient not found for age = " + age, exception.getMessage());
    }

}