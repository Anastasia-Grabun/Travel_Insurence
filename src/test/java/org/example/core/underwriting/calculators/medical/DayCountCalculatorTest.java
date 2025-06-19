package org.example.core.underwriting.calculators.medical;

import org.example.core.api.dto.AgreementDTO;
import org.example.core.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DayCountCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private DayCountCalculator calculator;

    private AgreementDTO agreementDTO;

    @BeforeEach
    void setUp() {
        agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(Date.from(LocalDate.of(2023, 4, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        agreementDTO.setAgreementDateTo(Date.from(LocalDate.of(2023, 4, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Test
    void shouldCalculateDayCountCorrectly() {
        long expectedDays = 10;
        when(dateTimeUtil.getDaysBetween(agreementDTO.getAgreementDateFrom(), agreementDTO.getAgreementDateTo())).thenReturn(expectedDays);
        BigDecimal result = calculator.calculate(agreementDTO);
        assertEquals(BigDecimal.valueOf(expectedDays), result);
    }

}