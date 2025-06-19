package org.example.core.services;

import org.example.core.api.command.TravelGetAllAgreementUuidsCoreCommand;
import org.example.core.api.command.TravelGetAllAgreementUuidsCoreResult;

public interface TravelGetAllAgreementUuidsService {

    TravelGetAllAgreementUuidsCoreResult getAgreement(TravelGetAllAgreementUuidsCoreCommand command);

}
