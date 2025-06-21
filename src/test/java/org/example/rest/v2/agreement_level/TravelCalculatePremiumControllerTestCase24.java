package org.example.rest.v2.agreement_level;

import org.example.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase24 extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare(getTestCaseFolderName());
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_24";
    }
}
