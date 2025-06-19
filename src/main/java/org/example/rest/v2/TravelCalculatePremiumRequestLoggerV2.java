package org.example.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLoggerV2 {

    private static final Logger logger = LogManager.getLogger(TravelCalculatePremiumRequestLoggerV2.class);

    public void toLogJson(TravelCalculatePremiumRequestV2 request){
        try {
            String json = new ObjectMapper().writeValueAsString(request);
            logger.info("REQUEST:" + json);
        } catch (JsonProcessingException e) {
            logger.error("Error with converting string to json", e);
        }
    }

}
