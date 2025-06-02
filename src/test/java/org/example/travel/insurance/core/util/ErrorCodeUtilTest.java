package org.example.travel.insurance.core.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@ExtendWith(MockitoExtension.class)
class ErrorCodeUtilTest {
    @InjectMocks
    private ErrorCodeUtil errorCodeUtil;

    @BeforeEach
    void setUp() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("CODE_1", "Error text");
        map.put("CODE_2", "Error with {PLACEHOLDER}");

        Field field = ErrorCodeUtil.class.getDeclaredField("errorMessages");
        field.setAccessible(true);
        field.set(errorCodeUtil, map);
    }

    @Test
    void getDescription_shouldReturnCorrectMessage() {
        String result = errorCodeUtil.getDescription("CODE_1");
        assertEquals("Error text", result);
    }

    @Test
    void getErrorDescription_shouldReplacePlaceholder() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "VALUE");
        String result = errorCodeUtil.getErrorDescription("CODE_2", List.of(placeholder));
        assertEquals("Error with VALUE", result);
    }

    @Test
    void getErrorDescription_shouldReturnNullForUnknownCode() {
        String result = errorCodeUtil.getErrorDescription("UNKNOWN", List.of());
        assertNull(result);
    }

}
