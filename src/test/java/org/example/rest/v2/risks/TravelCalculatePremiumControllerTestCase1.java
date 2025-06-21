package org.example.rest.v2.risks;

import org.example.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase1 extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare(getTestCaseFolderName(), true);
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_1";
    }
}
