package org.example.core.services;

import org.example.core.api.command.TravelCalculatePremiumCoreCommand;
import org.example.core.api.command.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}
