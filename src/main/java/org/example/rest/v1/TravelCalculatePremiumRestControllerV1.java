package org.example.rest.v1;

import com.google.common.base.Stopwatch;
import org.example.core.api.command.TravelCalculatePremiumCoreCommand;
import org.example.core.api.command.TravelCalculatePremiumCoreResult;
import org.example.core.services.TravelCalculatePremiumService;
import org.example.dto.v1.DtoV1Converter;
import org.example.dto.v1.TravelCalculatePremiumRequestV1;
import org.example.dto.v1.TravelCalculatePremiumResponseV1;
import org.example.rest.common.TravelRestRequestExecutionTimeLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumRestControllerV1 {

	private final TravelCalculatePremiumRequestLoggerV1 requestLogger;
	private final TravelCalculatePremiumResponseLoggerV1 responseLogger;
	private final TravelRestRequestExecutionTimeLogger executionTimeLogger;
	private final TravelCalculatePremiumService calculatePremiumService;
	private final DtoV1Converter dtoV1Converter;

	TravelCalculatePremiumRestControllerV1(TravelCalculatePremiumRequestLoggerV1 requestLogger,
										   TravelCalculatePremiumResponseLoggerV1 responseLogger,
										   TravelRestRequestExecutionTimeLogger executionTimeLogger,
										   TravelCalculatePremiumService calculatePremiumService,
										   DtoV1Converter dtoV1Converter) {
		this.requestLogger = requestLogger;
		this.responseLogger = responseLogger;
		this.executionTimeLogger = executionTimeLogger;
		this.calculatePremiumService = calculatePremiumService;
		this.dtoV1Converter = dtoV1Converter;
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = processRequest(request);
		executionTimeLogger.toExecuteTimeLogger(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
		requestLogger.toLogJson(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV1Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV1 response = dtoV1Converter.buildResponse(coreResult);
		responseLogger.toLogJson(response);
		return response;
	}

}