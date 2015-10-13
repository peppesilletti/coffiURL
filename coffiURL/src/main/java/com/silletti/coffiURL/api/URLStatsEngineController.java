package com.silletti.coffiURL.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.silletti.coffiURL.core.URLStatsEngine;
import com.silletti.coffiURL.core.URLStatsEngineInt;
import com.silletti.coffiURL.entities.Statistics;
import com.silletti.coffiURL.json.JSONResponse;

@RestController
@RequestMapping("/api/stats")
public class URLStatsEngineController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody JSONResponse<Statistics> getStats(
			@RequestParam(value = "shortURL") String shortURL,
			@RequestParam(value = "fromTime", defaultValue = "") String fromTime,
			@RequestParam(value = "toTime", defaultValue = "") String toTime)
					throws MissingServletRequestParameterException { 
		
		if (shortURL.isEmpty() || shortURL == null) {
			throw new MissingServletRequestParameterException(shortURL, "String");
		} else {
			
			URLStatsEngineInt engine = new URLStatsEngine();
			
			if (fromTime.isEmpty() || toTime.isEmpty()) {
			
				Statistics stats = engine.getStats(shortURL);
				
				return new JSONResponse<Statistics>("Success!", HttpServletResponse.SC_OK, stats);
				
			} else {
				Statistics stats = engine.getStats
						(shortURL, Long.valueOf(fromTime), Long.valueOf(toTime));
				
				return new JSONResponse<Statistics>("Success!", HttpServletResponse.SC_OK, stats);
			}
		}
		
	}
	
	@ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class})
	void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again and insert all requested parameters");
	}
	
}
