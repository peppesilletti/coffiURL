package com.silletti.coffiURL.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class URLStatsEngineController {

	@RequestMapping(method = RequestMethod.GET)
	public String getStats(
			@RequestParam(value = "shortURL") String shortURL,
			@RequestParam(value = "fromTime", defaultValue = "") String fromTime,
			@RequestParam(value = "toTime", defaultValue = "") String toTime) { //sarà JSON
		return null;
	}
	
}
