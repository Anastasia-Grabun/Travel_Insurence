package org.example.rest.internal;

import com.google.common.base.Stopwatch;
import org.example.core.api.command.TravelGetAgreementCoreCommand;
import org.example.core.api.command.TravelGetAgreementCoreResult;
import org.example.core.services.TravelGetAgreementService;
import org.example.dto.internal.GetAgreementDtoConverter;
import org.example.dto.internal.TravelGetAgreementResponse;
import org.example.rest.common.TravelRestRequestExecutionTimeLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementRestController {

	private final TravelGetAgreementRequestLogger requestLogger;
	private final TravelGetAgreementResponseLogger responseLogger;
	private final TravelRestRequestExecutionTimeLogger executionTimeLogger;
	private final TravelGetAgreementService getAgreementService;
	private final GetAgreementDtoConverter dtoConverter;

	TravelGetAgreementRestController(TravelGetAgreementRequestLogger requestLogger,
									 TravelGetAgreementResponseLogger responseLogger,
									 TravelRestRequestExecutionTimeLogger executionTimeLogger,
									 TravelGetAgreementService getAgreementService,
									 GetAgreementDtoConverter dtoConverter) {
		this.requestLogger = requestLogger;
		this.responseLogger = responseLogger;
		this.executionTimeLogger = executionTimeLogger;
		this.getAgreementService = getAgreementService;
		this.dtoConverter = dtoConverter;
	}

	@GetMapping(path = "/{uuid}",
			produces = "application/json")
	public TravelGetAgreementResponse getAgreement(@PathVariable("uuid") String uuid) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelGetAgreementResponse response = processRequest(uuid);
		executionTimeLogger.toExecuteTimeLogger(stopwatch);
		return response;
	}

	private TravelGetAgreementResponse processRequest(String uuid) {
		requestLogger.log(uuid);
		TravelGetAgreementCoreCommand coreCommand = dtoConverter.buildCoreCommand(uuid);
		TravelGetAgreementCoreResult coreResult = getAgreementService.getAgreement(coreCommand);
		TravelGetAgreementResponse response = dtoConverter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}