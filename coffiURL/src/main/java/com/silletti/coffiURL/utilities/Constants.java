package com.silletti.coffiURL.utilities;

/**
 * Class that contains all the constants used in the classes.
 * */
public class Constants {
	
	//Constans for the URL and statistics proprerties
	public static final String LONGURL = "longURL";
	public static final String SHORTURL = "shortURL";
	public static final String BROWSER = "browser";
	public static final String PLATFORM = "platform";
	public static final String LOCATION = "location";
	public static final String TIMESTAMP = "timestamp";
	public static final String IPADRESS = "ipAdress";
	public static final String NUMOFCLICKS = "numOfClicks";
	
	public static final String MINTIME = "-inf";
	public static final String MAXTIME = "+inf";
	
	public static final Integer LENGTHSHORTURL = 6;
	
	//DAO costants
	public static final String DAODONE = "OK";
	
	//Jedis connection constants
	public static final String LOCALHOST = "172.17.0.2";
	public static final Integer PORT = 6379;
	
	
	
	private Constants() {}
	
}
