package com.silletti.coffiURL.entities;

import java.util.Date;

/**
 * POJO class for the url object.
 * */
public class URLInfo {

	private String timestamp;
	private String browser;
	private String platform;
	private String geoLocation;
	private String ipAdress;
	
	public URLInfo() {	
		Date date = new Date();
		this.timestamp = String.valueOf(date.getTime());
	}
	
	public URLInfo(String browser,
			String platform, String geoLocation, String ipAdress) { 
		
		Date date = new Date();
		this.timestamp = String.valueOf(date.getTime());
		
		this.browser = browser;
		this.platform = platform;
		this.geoLocation = geoLocation;
		this.ipAdress = ipAdress;
		
	}

	public String getIpAdress() {
		return ipAdress;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public String getBrowser() {
		return browser;
	}

	public String getPlatform() {
		return platform;
	}

	public String getGeoLocation() {
		return geoLocation;
	}
	
	public String toString() {
		return "browser:"+this.browser+", ipAdress:"+this.ipAdress+", "
				+ "timestamp:"+this.timestamp+", platform:"+this.platform+", "
				+ "location:"+this.geoLocation;
	}
	
}
