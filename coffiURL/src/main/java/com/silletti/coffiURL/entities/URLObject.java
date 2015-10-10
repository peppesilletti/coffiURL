package com.silletti.coffiURL.entities;

/**
 * POJO class for URL entity.
 * */
public class URLObject {

	private String shortURL;
	private String longURL;
	
	public URLObject(String shortURL, String longURL) {
		this.shortURL = shortURL;
		this.longURL = longURL;
	}

	public String getShortURL() {
		return shortURL;
	}

	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}

	public String getLongURL() {
		return longURL;
	}

	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}
	
	

}
