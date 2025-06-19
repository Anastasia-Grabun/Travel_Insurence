package org.example.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumResponseLoggerV1 {

    private static final Logger logger = LogManager.getLogger(TravelCalculatePremiumResponseLoggerV1.class);

    void toLogJson(TravelCalculatePremiumResponseV1 response){
        try {
            String json = new ObjectMapper().writeValueAsString(response);
            logger.info("RESPONSE:" + json);
        } catch (JsonProcessingException e) {
            logger.error("Error with converting string to json", e);
        }
    }

}
