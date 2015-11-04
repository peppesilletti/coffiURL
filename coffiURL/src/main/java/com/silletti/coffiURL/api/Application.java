package com.silletti.coffiURL.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.silletti.coffiURL.utilities.Blacklist;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	
    	if (args.length != 0) {
    			if (args[0].equals("init")) {
		    		Blacklist bl = new Blacklist();
		    		bl.initRepository();
    			}
    	}
    	
        SpringApplication.run(Application.class, args);
    }
}
