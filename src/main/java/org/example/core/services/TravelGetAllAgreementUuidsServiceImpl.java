package org.example.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.example.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import org.example.core.repositories.entities.AgreementEntityRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelGetAllAgreementUuidsServiceImpl
        implements TravelGetAllAgreementUuidsService {

    private final AgreementEntityRepository agreementEntityRepository;

    @Override
    public TravelGetNotExportedAgreementUuidsCoreResult getAgreement(TravelGetNotExportedAgreementUuidsCoreCommand command) {
        List<String> agreementUuids = agreementEntityRepository.getNotExportedAgreementUuids();
        return new TravelGetNotExportedAgreementUuidsCoreResult(null, agreementUuids);
    }

}
