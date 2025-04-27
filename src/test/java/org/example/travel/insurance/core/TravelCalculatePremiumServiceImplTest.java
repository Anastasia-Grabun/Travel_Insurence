package org.example.travel.insurance.core;

import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelPremiumUnderwriting travelPremiumUnderwriting;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void shouldPopulateResponseWithCorrectFirstName() {
        var request = createRequestWithAllFields();

        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var actual = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), actual.getPersonFirstName());
    }

    @Test
    public void shouldPopulateResponseWithCorrectLastName(){
        var request = createRequestWithAllFields();

        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var actual = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), actual.getPersonLastName());
    }

    @Test
    public void shouldPopulateResponseWithCorrectAgreementDateFrom(){
        var request = createRequestWithAllFields();

        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var actual = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), actual.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateResponseWithCorrectAgreementDateTo(){
        var request = createRequestWithAllFields();

        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var actual = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), actual.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateResponseWithCorrectAgreementPrice(){
        var request = createRequestWithAllFields();

        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var actual = service.calculatePremium(request);
        assertNotNull(actual.getAgreementPrice());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Mickie");
        request.setPersonLastName("Green");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        return request;
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");

        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);

        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithCorrectErrorCount() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");

        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);

        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldReturnResponseWithCorrectError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");

        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);

        assertEquals(response.getErrors().getFirst().getField(), "field");
        assertEquals(response.getErrors().getFirst().getMessage(), "message");
        assertNull(response.getPersonFirstName());
    }

    @Test
    public void allFieldsMustBeEmptyWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");

        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);

        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }

    @Test
    public void shouldNOtBeInteractionWithDateTimeServiceWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");

        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        service.calculatePremium(request);

        verifyNoInteractions(travelPremiumUnderwriting);
    }

}

