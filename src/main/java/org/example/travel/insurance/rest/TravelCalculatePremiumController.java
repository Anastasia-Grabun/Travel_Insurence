package org.example.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.travel.insurance.core.TravelCalculatePremiumService;
import org.example.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.example.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumController {

	private final TravelCalculatePremiumService calculatePremiumService;
	private final TravelCalculatePremiumRequestLogger loggerRequest;
	private final TravelCalculatePremiumResponseLogger loggerResponse;
	private final TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponse response = processRequest(request);
		timeLogger.toExecuteTimeLogger(stopwatch);

		return response;
	}

	private TravelCalculatePremiumResponse processRequest(TravelCalculatePremiumRequest request){
		loggerRequest.toLogJson(request);
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		loggerResponse.toLogJson(response);

		return response;
	}

}