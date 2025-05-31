package org.example.travel.insurance.core.underwriting;

import org.example.travel.insurance.core.util.DateTimeUtil;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwritingImpl;

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(parseDate("01.01.2024"));
        when(request.getAgreementDateTo()).thenReturn(parseDate("10.01.2024"));
        when(dateTimeUtil.calculateDurationInDays(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(9L);
        BigDecimal premium = travelPremiumUnderwritingImpl.calculatePremium(request);

        assertEquals(premium, new BigDecimal(9));
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
