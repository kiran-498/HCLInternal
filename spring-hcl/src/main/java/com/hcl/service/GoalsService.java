package com.hcl.service;

import java.util.List;

import com.hcl.entity.Goals;

public interface GoalsService {

	List<Goals> getAllgoalsByYear(String year);

	List<Goals> getSpecificGoal(String level);

	void updateOrSaveTheGoal(Goals goals);

	List<Goals> getSpecificduGoal(String level);
	//////////////////////////////////////////////////

	List<Goals> selectUtilizationGoal(String maxDateInGoal);

}
