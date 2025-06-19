package org.example.core.services;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.command.TravelGetAllAgreementUuidsCoreCommand;
import org.example.core.api.command.TravelGetAllAgreementUuidsCoreResult;
import org.example.core.repositories.entities.AgreementEntityRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelGetAllAgreementUuidsServiceImpl
        implements TravelGetAllAgreementUuidsService {

    private final AgreementEntityRepository agreementEntityRepository;

    @Override
    public TravelGetAllAgreementUuidsCoreResult getAgreement(TravelGetAllAgreementUuidsCoreCommand command) {
        List<String> agreementUuids = agreementEntityRepository.getAllAgreementUuids();
        return new TravelGetAllAgreementUuidsCoreResult(null, agreementUuids);
    }

}
