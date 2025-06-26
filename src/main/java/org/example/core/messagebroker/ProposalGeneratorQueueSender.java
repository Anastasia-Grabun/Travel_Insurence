package org.example.core.messagebroker;

import org.example.core.api.dto.AgreementDTO;

public interface ProposalGeneratorQueueSender {

    void send(AgreementDTO agreement);

}
