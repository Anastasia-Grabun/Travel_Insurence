package org.example.rest.v1.person;

import org.example.rest.v1.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase6 extends TravelCalculatePremiumControllerTestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare(getTestCaseFolderName());
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_6";
    }
}
