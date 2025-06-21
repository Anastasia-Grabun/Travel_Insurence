package org.example.rest.v2.risk_travel_medical;

import org.example.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase26 extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare(getTestCaseFolderName());
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_26";
    }
}
