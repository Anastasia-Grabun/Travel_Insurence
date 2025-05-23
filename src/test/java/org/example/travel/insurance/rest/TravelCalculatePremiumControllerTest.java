package org.example.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void firstNameNotProvided() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_firstName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_firstName_not_provided.json"
        );
    }

    @Test
    public void lastNameNotProvided() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_lastName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_lastName_not_provided.json"
        );
    }

    @Test
    public void agreementDateFromNotProvided() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    public void agreementDateToNotProvided() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    public void selectedRisksAreNull() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_selected_risks_null.json",
                "rest/TravelCalculatePremiumResponse_selected_risks_null.json"
        );
    }

    @Test
    public void selectedRisksAreEmpty() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_selected_risks_empty.json",
                "rest/TravelCalculatePremiumResponse_selected_risks_empty.json"
        );
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }

    @Test
    public void successRequest() throws Exception {
        compareRequestResponse(
                "rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }
    private void compareRequestResponse(String jsonRequestFilePath, String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        assertJson(responseBodyContent)
                .where().arrayInAnyOrder()
                .isEqualTo(jsonResponse);
    }

}
