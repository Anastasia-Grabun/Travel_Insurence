package org.example.core.services;

import org.example.core.api.command.TravelGetAgreementCoreCommand;
import org.example.core.api.command.TravelGetAgreementCoreResult;

public interface TravelGetAgreementService {

    TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);

}
