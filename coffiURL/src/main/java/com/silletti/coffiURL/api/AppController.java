package com.silletti.coffiURL.api;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	    public String redirect(@PathVariable String shortURL) {
		 
		 if (shortURL.isEmpty() || shortURL == null ) {
			 return null;
		 } else {
			 URLShortenerEngine engine = new URLShortenerEngine();
			 
			 String longURL = engine.getLongURL(shortURL);
			 
			 if (longURL == null) {
				 return null;
			 } else  {
				 URLStatsEngine statsEngine = new URLStatsEngine();
				 UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
				 ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
			 
				 String ip = "176.32.19.77";
				 
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
	 }
	 
	 private String getLocation(String ip) {
		 File db = new File("./GeoLite2-Country.mmdb");
		 String cc = "";
		 try {
			DatabaseReader reader = new DatabaseReader.Builder(db).build();
			InetAddress ipAddress = InetAddress.getByName(ip);
			
			CountryResponse response = reader.country(ipAddress);
            Country country = response.getCountry();
            cc = country.getIsoCode();
            
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return cc;
	 }
	
}
