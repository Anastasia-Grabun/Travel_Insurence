package org.example.travel.insurance.core;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
class DateTimeService {

    long calculateDurationInDays(Date date1, Date date2){
        long differenceInMilliseconds = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
    }

}
