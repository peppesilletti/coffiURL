package com.silletti.coffiURL.entities;

import java.util.HashMap;
import java.util.Map;

import com.silletti.coffiURL.utilities.Constants;

public class Statistics {
	Map<String, Map<String, Integer>> statistics;
	
	public Statistics() {
		
		statistics = new HashMap<String, Map<String,Integer>>();
	}
	
	public Map<String, Map<String, Integer>> getStatistics() {
		return statistics;
	}
	
	public void addStatistic(String type, Map<String, Integer> s) {
		this.statistics.put(type, s);
	}
}
