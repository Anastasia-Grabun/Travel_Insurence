package org.example.rest.v2.person_level;

import org.example.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase3 extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_3";
    }
}
