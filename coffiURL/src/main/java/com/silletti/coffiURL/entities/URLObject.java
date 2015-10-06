package com.silletti.coffiURL.entities;

/**
 * Bean class for the url object.
 * */
public class URLObject {
	private String url;
	private String timestamp;
	private String browser;
	private String platform;
	private String geoLocation;
	private String ipAdress;
	private String numOfClicks;
	
	public URLObject() {	}
	
	public URLObject(String url, String timestamp, String browser,
			String platform, String geoLocation, String clicks,
			String ipAdress) { 
		
		this.url = url;
		this.timestamp = timestamp;
		this.browser = browser;
		this.platform = platform;
		this.geoLocation = geoLocation;
		this.numOfClicks = clicks;
		this.ipAdress = ipAdress;
		
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
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
