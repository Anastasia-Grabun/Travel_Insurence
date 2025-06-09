package org.example.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.example.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.example.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
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
    public TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> errors = requestValidator.validate(request);

        return errors.isEmpty()
                ? buildResponse(request, premiumUnderwriting.calculatePremium(request))
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponseV1 buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponseV1(errors);
    }

    private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumRequestV1 request,
                                                           TravelPremiumCalculationResult premiumCalculationResult) {
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
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
