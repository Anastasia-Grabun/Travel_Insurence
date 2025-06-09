package org.example.travel.insurance.core.util;

import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeUtil {

    public long calculateDurationInDays(Date date1, Date date2){
        long differenceInMilliseconds = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
    }

    public Date getCurrentDateTime() {
        ZoneId zone = ZoneId.of("Europe/Riga");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
        return Date.from(zonedDateTime.toInstant());
    }

}
