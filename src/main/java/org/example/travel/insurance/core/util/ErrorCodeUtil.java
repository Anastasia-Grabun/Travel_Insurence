package org.example.travel.insurance.core.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class ErrorCodeUtil {

    @Value("classpath:errorCodes.properties")
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
        return errorMessages.get(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders){
        String errorDescription = errorMessages.get(errorCode);
        for(Placeholder placeholder : placeholders){
            String replacePlaceholder = "{" + placeholder.getPlaceholderName() + "}";
            errorDescription = errorDescription.replace(replacePlaceholder, placeholder.getPlaceholderValue());
        }

        return errorDescription;
    }

}

