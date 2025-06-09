package org.example.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLogger {

    private static final Logger logger = LogManager.getLogger(TravelCalculatePremiumRequestLogger.class);

    void toLogJson(TravelCalculatePremiumRequestV1 request){
        try {
            String json = new ObjectMapper().writeValueAsString(request);
            logger.info("REQUEST:" + json);
        } catch (JsonProcessingException e) {
            logger.error("Error with converting string to json", e);
        }
    }

}
