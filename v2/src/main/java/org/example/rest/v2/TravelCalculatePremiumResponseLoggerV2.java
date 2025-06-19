package org.example.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumResponseLoggerV2 {

    private static final Logger logger = LogManager.getLogger(TravelCalculatePremiumResponseLoggerV2.class);

    public void toLogJson(TravelCalculatePremiumResponseV2 response){
        try {
            String json = new ObjectMapper().writeValueAsString(response);
            logger.info("RESPONSE:" + json);
        } catch (JsonProcessingException e) {
            logger.error("Error with converting string to json", e);
        }
    }

}
