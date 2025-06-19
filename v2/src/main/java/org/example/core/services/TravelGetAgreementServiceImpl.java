package org.example.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.command.TravelGetAgreementCoreCommand;
import org.example.core.api.command.TravelGetAgreementCoreResult;
import org.example.core.api.dto.ValidationErrorDTO;
import org.example.core.validations.TravelAgreementUuidValidator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelGetAgreementServiceImpl implements TravelGetAgreementService {

    private final TravelAgreementUuidValidator agreementUuidValidator;
    private final AgreementDTOLoader agreementDTOLoader;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementUuidValidator.validate(command.getUuid());
        return errors.isEmpty()
                ? buildResponse(command.getUuid())
                : buildResponse(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelGetAgreementCoreResult(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(String agreementUuid) {
        TravelGetAgreementCoreResult coreResult = new TravelGetAgreementCoreResult();
        coreResult.setAgreement(agreementDTOLoader.load(agreementUuid));
        return coreResult;
    }

}
