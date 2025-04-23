package org.example.travel.insurance.core;

import org.example.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateTimeServiceTest {
    private DateTimeService dateTimeService = new DateTimeService();
    private TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

    @Test
    public void shouldDurationInDaysPositive(){
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        var date = dateTimeService.calculateDurationInDays(request);

        assertNotNull(date);
    }

    @Test
    public void shouldDurationInDaysNegative(){
        Date dateFrom = convertToDate("2025-12-01");
        Date dateTo = convertToDate("2023-12-01");

        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        var date = dateTimeService.calculateDurationInDays(request);

        assertNotNull(date);
    }

    @Test
    public void shouldDurationInDaysZero(){
        Date dateFrom = convertToDate("2025-12-01");
        Date dateTo = convertToDate("2025-12-01");

        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        var date = dateTimeService.calculateDurationInDays(request);

        assertNotNull(date);
    }

    private Date convertToDate(String date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
