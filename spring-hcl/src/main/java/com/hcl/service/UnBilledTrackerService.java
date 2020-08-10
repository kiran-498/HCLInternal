package com.hcl.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.hcl.entity.UnBilledTracker;


public interface UnBilledTrackerService {
	//Sai Begins
	Map<String, Map<String, Map<String, Integer>>> getUnBilledDetails();

	Map<String, Map<String, Map<String, Integer>>> getUnBilled4Data();

	List<JSONObject> getUnbilledL3PlanData(String type, String column1, String column);

	//Alekhya Begins
	Map<String, List<UnBilledTracker>> findWithCatAndPlan(String categoryType, String plan);

	Map<String, List<UnBilledTracker>> FindByCatAndAge(String categoryType, String age);
	
	//Varun Begins
	List<UnBilledTracker> FindByPlanAndDu(String du, String plan);

	List<UnBilledTracker> FindByDuAndAge(String du, String age);

	List<UnBilledTracker> FindByDuAndYearAndMonth(String du, String month, String year);
}
