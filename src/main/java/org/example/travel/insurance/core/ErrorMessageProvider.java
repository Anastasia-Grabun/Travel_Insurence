package org.example.travel.insurance.core;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class ErrorMessageProvider {

    @Value("errorCodes.properties")
    private Resource file;

    private final Map<String, String> errorMessages = new HashMap<>();

    @PostConstruct
    public void loadMessages() throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);

            for (String key : properties.stringPropertyNames()) {
                errorMessages.put(key, properties.getProperty(key));
            }
        }
    }

    public String getDescription(String errorCode) {
        return errorMessages.getOrDefault(errorCode, "Unknown error");
    }

}

