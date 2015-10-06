package com.silletti.coffiURL.entities;

/**
 * Bean class for statistics.
 * */
public class Statistics {

	
	private String longURL;
	private String timestamp;
	private String browser;
	private String platform;
	private String geoLocation;
	private String ipAdress;
	private String numOfClicks;

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getLongUrl() {
		return longURL;
	}

	public void setLongUrl(String url) {
		this.longURL = url;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getNumOfClicks() {
		return numOfClicks;
	}

	public void setNumOfClicks(String numOfClicks) {
		this.numOfClicks = numOfClicks;
	}
	
}
