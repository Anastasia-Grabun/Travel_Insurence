package org.example.travel.insurance.core;

import org.example.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.example.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @Test
    public void shouldPopulateResponseWithCorrectFirstName() {
        TravelCalculatePremiumRequest request = createRequestWithAllFields();

        TravelCalculatePremiumResponse actual = service.calculatePremium(request);

        assertEquals(request.getPersonFirstName(), actual.getPersonFirstName());
    }

    @Test
    public void shouldPopulateResponseWithCorrectLastName(){
        TravelCalculatePremiumRequest request = createRequestWithAllFields();

        TravelCalculatePremiumResponse actual = service.calculatePremium(request);

        assertEquals(request.getPersonLastName(), actual.getPersonLastName());
    }

    @Test
    public void shouldPopulateResponseWithCorrectAgreementDateFrom(){
        TravelCalculatePremiumRequest request = createRequestWithAllFields();

        TravelCalculatePremiumResponse actual = service.calculatePremium(request);

        assertEquals(request.getAgreementDateFrom(), actual.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateResponseWithCorrectAgreementDateTo(){
        TravelCalculatePremiumRequest request = createRequestWithAllFields();

        TravelCalculatePremiumResponse actual = service.calculatePremium(request);

        assertEquals(request.getAgreementDateTo(), actual.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateResponseWithCorrectAgreementPrice(){
        TravelCalculatePremiumRequest request = createRequestWithAllFields();

        TravelCalculatePremiumResponse actual = service.calculatePremium(request);

        assertNotNull(actual.getAgreementPrice());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Mickie");
        request.setPersonLastName("Green");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        return request;
    }

}