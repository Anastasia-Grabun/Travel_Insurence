package org.example.core.services;

import org.example.core.api.command.TravelExportAgreementToXmlCoreCommand;
import org.example.core.api.command.TravelExportAgreementToXmlCoreResult;

public interface TravelExportAgreementToXmlService {

    TravelExportAgreementToXmlCoreResult export(TravelExportAgreementToXmlCoreCommand command);

}
