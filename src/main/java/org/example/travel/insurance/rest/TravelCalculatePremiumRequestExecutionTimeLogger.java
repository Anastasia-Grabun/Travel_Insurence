package org.example.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumRequestExecutionTimeLogger {

    private static final Logger logger = LogManager.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);

    void toExecuteTimeLogger(Stopwatch stopwatch){
        stopwatch.stop();
        long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        logger.info("Request processing time (ms): " + time);
    }

}
