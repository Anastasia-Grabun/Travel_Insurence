package org.example.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLoggerV1 {

    private static final Logger logger = LogManager.getLogger(TravelCalculatePremiumRequestLoggerV1.class);

    void toLogJson(TravelCalculatePremiumRequestV1 request){
        try {
            String json = new ObjectMapper().writeValueAsString(request);
            logger.info("REQUEST:" + json);
        } catch (JsonProcessingException e) {
            logger.error("Error with converting string to json", e);
        }
    }

}
