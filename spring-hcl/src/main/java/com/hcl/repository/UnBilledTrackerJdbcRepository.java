package com.hcl.repository;

import java.sql.Date;
import java.util.List;

import com.hcl.entity.UnBilledTracker;

public interface UnBilledTrackerJdbcRepository {

	List<UnBilledTracker> getEBDLWDTRFOutDateGreaterThanEqual(Date date);
	public List<UnBilledTracker> findAllByCategoryType(String categoryType);
	public List<UnBilledTracker> findAllByCategoryTypeAndMonthAndYear(String categoryType, int year, int month);
	public List<UnBilledTracker> findAllByCategoryTypeAndYear(String categoryType, int year);
	public List<UnBilledTracker> findWithCatAndPlan(String categoryType, String plan);
	public List<UnBilledTracker> findWithCatAndAge(String categoryType, String age);
}
