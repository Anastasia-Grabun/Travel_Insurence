package org.example.rest.common;

import com.google.common.base.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class TravelRestRequestExecutionTimeLogger {

    private static final Logger logger = LogManager.getLogger(TravelRestRequestExecutionTimeLogger.class);

    public void toExecuteTimeLogger(Stopwatch stopwatch){
        stopwatch.stop();
        long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        logger.info("Request processing time (ms): " + time);
    }

}
