package com.silletti.coffiURL.api;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import com.silletti.coffiURL.core.URLShortenerEngine;
import com.silletti.coffiURL.core.URLStatsEngine;
import com.silletti.coffiURL.entities.URLInfo;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

@Controller
public class AppController {

	@Autowired
    private HttpServletRequest request;
	
	 @RequestMapping(value={"/", "/index.html"})
	    public String home(){
	        return "redirect:/app/index.html";
	  }
	 
	 @RequestMapping("{shortURL}")
	 	public String redirect(@PathVariable String shortURL) throws IOException, GeoIp2Exception {
		 
			 URLShortenerEngine engine = new URLShortenerEngine();
			 
			 String longURL = engine.getLongURL(shortURL);
			 
			 if (longURL == null) {
				 throw new ShortUrlNotFoundException(shortURL);
			 } else  {
				 URLStatsEngine statsEngine = new URLStatsEngine();
				 UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
				 ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
			 
				 String ip = "93.39.165.118"; //request.getRemoteAddr();
				 
				 URLInfo info = new URLInfo(
						 agent.getName(),  // es. Mozilla, Chrome
						 agent.getOperatingSystem().getName(), //es Linux, Windows
						 getLocation(ip),  //es IT, US
						 ip //es 127.12.25.365
				  );
				 
				 statsEngine.addStats(shortURL, info);

			     return "redirect:"+longURL;
			 }
	 }
	 
	 @RequestMapping("{shortURL}$")
	    public String statistics(@PathVariable String shortURL) {
		 URLShortenerEngine engine = new URLShortenerEngine();
		 
		 String longURL = engine.getLongURL(shortURL);
		 
		 if (longURL == null) {
			 throw new ShortUrlNotFoundException(shortURL);
		 } else  {
		 		return "redirect:/app/statistics.html?shortURL="+shortURL;
		 }
	 	}
	 
	 private String getLocation(String ip) throws IOException, GeoIp2Exception {
		 File db = new File("./GeoLite2-Country.mmdb");
		 String cc = "";

		 DatabaseReader reader = new DatabaseReader.Builder(db).build();
		 InetAddress ipAddress = InetAddress.getByName(ip);
		
		 CountryResponse response = reader.country(ipAddress);
         Country country = response.getCountry();
         cc = country.getIsoCode();
         		 
		  return cc;
	 }
	 
	 @ExceptionHandler({IOException.class, GeoIp2Exception.class})
		void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
		    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "A server error accurred. Please try again or come back later.");
		}
	
}
