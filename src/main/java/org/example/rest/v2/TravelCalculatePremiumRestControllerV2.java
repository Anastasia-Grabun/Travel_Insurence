package org.example.rest.v2;

import com.google.common.base.Stopwatch;
import org.example.core.api.command.TravelCalculatePremiumCoreCommand;
import org.example.core.api.command.TravelCalculatePremiumCoreResult;
import org.example.core.services.TravelCalculatePremiumService;
import org.example.dto.v2.DtoV2Converter;
import org.example.dto.v2.TravelCalculatePremiumRequestV2;
import org.example.dto.v2.TravelCalculatePremiumResponseV2;
import org.example.rest.common.TravelRestRequestExecutionTimeLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
public class TravelCalculatePremiumRestControllerV2 {

	private final TravelCalculatePremiumRequestLoggerV2 requestLogger;
	private final TravelCalculatePremiumResponseLoggerV2 responseLogger;
	private final TravelRestRequestExecutionTimeLogger executionTimeLogger;
	private final TravelCalculatePremiumService calculatePremiumService;
	private final DtoV2Converter dtoV2Converter;

	TravelCalculatePremiumRestControllerV2(TravelCalculatePremiumRequestLoggerV2 requestLogger,
										   TravelCalculatePremiumResponseLoggerV2 responseLogger,
										   TravelRestRequestExecutionTimeLogger executionTimeLogger,
										   TravelCalculatePremiumService calculatePremiumService,
										   DtoV2Converter dtoV2Converter) {
		this.requestLogger = requestLogger;
		this.responseLogger = responseLogger;
		this.executionTimeLogger = executionTimeLogger;
		this.calculatePremiumService = calculatePremiumService;
		this.dtoV2Converter = dtoV2Converter;
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV2 response = processRequest(request);
		executionTimeLogger.toExecuteTimeLogger(stopwatch);

		return response;
	}

	private TravelCalculatePremiumResponseV2 processRequest(TravelCalculatePremiumRequestV2 request) {
		requestLogger.toLogJson(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildResponse(coreResult);
		responseLogger.toLogJson(response);

		return response;
	}

}