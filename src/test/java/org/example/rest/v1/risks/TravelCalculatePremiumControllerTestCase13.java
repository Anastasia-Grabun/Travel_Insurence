package org.example.rest.v1.risks;

import org.example.rest.v1.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase13 extends TravelCalculatePremiumControllerTestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_13";
    }
}
