package com.silletti.coffiURL.entities;

import java.io.Serializable;

/**
 * Bean class for the url object.
 * */
public class Statistics {

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	private static final long serialVersionUID = 1L;
	private String timestamp;
	private String browser;
	private String platform;
	private String geoLocation;
	private String ipAdress;
	
	public Statistics() {	}
	
	public Statistics(String timestamp, String browser,
			String platform, String geoLocation, String ipAdress) { 
		
		this.timestamp = timestamp;
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
