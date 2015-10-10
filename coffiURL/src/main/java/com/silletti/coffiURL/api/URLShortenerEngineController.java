package com.silletti.coffiURL.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.silletti.coffiURL.core.URLShortenerEngine;
import com.silletti.coffiURL.core.URLShortenerEngineInt;
import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.json.JSONResponse;

@RestController
@RequestMapping("/api/url")
public class URLShortenerEngineController {

    @RequestMapping(method = RequestMethod.GET) 
    public JSONResponse<URLObject> getURL(
    		@RequestParam(value="url") String url) 
    				throws MissingServletRequestParameterException {
    	
    	if (url.isEmpty()) {
     	   throw new MissingServletRequestParameterException(url, "String");
     	} else {
     	
 	    	URLShortenerEngineInt engine = new URLShortenerEngine();
 	    	
 	    	String longURL = engine.getLongURL(url);
 	    	
 	    	if (longURL == null) {
 	    		throw new IllegalArgumentException();
 	    	} else {
 	    		return new JSONResponse<URLObject>("Success!", 
 	    				HttpServletResponse.SC_OK, new URLObject(url, longURL));
 	    	}
     	}
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    public JSONResponse<URLObject> addNewURL( 
    		@RequestParam(value = "longURL") String longURL,
    		@RequestParam(value = "shortURL", defaultValue = "") String shortURL)
    				throws MissingServletRequestParameterException {
    	
    	if (longURL.isEmpty()) {
    	   throw new MissingServletRequestParameterException(longURL, "String");
    	} else {
    	
	    	URLShortenerEngineInt engine = new URLShortenerEngine();
	    	
	    	if (shortURL.isEmpty()) {
	    		shortURL = engine.generateShortURL(longURL);
	    		
	    		if (shortURL == null)  {
	    			throw new IllegalArgumentException();
	    		}
	    		
	    	} else {
	    		Boolean r = engine.createCustomURL(shortURL, longURL);
		    	
		    	if (!r) {
		    		throw new IllegalArgumentException();
		    	} 		    		
	    	}
	    	
	    	return new JSONResponse<URLObject>("Success!", 
    				HttpServletResponse.SC_OK, new URLObject(shortURL, longURL));
    	}
    	
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody JSONResponse<String> missingParam(MissingServletRequestParameterException e) {
    	return new JSONResponse<String>("Missing input, please insert it.", 
    			HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }
 
    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody JSONResponse<String> invalidParam(IllegalArgumentException e) {
    	return new JSONResponse<String>("Invalid input, please change.", 
    			HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    } 
}
;