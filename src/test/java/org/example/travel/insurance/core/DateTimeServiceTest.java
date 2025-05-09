package org.example.travel.insurance.core;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldDurationInDaysPositive(){
        Date dateFrom = new Date();
        Date dateTo = new Date();

        var date = dateTimeService.calculateDurationInDays(dateFrom, dateTo);

        assertNotNull(date);
    }

    @Test
    public void shouldDurationInDaysNegative(){
        Date dateFrom = convertToDate("2025-12-01");
        Date dateTo = convertToDate("2023-12-01");

        var date = dateTimeService.calculateDurationInDays(dateFrom, dateTo);

        assertNotNull(date);
    }

    @Test
    public void shouldDurationInDaysZero(){
        Date dateFrom = convertToDate("2025-12-01");
        Date dateTo = convertToDate("2025-12-01");

        var date = dateTimeService.calculateDurationInDays(dateFrom, dateTo);

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
