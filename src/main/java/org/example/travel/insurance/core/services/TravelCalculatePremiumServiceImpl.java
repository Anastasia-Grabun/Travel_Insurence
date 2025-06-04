package org.example.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.example.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.example.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.example.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);

        return errors.isEmpty()
                ? buildResponse(request, premiumUnderwriting.calculatePremium(request))
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request,
                                                         TravelPremiumCalculationResult premiumCalculationResult) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setPersonBirthDate(request.getPersonBirthDate());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setCountry(request.getCountry());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        response.setAgreementPremium(premiumCalculationResult.totalPremium());
        response.setRisks(premiumCalculationResult.riskPremiums());

        return response;
    }

}
