package org.example.core.services;

import org.example.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.example.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;

public interface TravelGetAllAgreementUuidsService {

    TravelGetNotExportedAgreementUuidsCoreResult getAgreement(TravelGetNotExportedAgreementUuidsCoreCommand command);

}
