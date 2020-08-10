package com.hcl.service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.entity.Goals;
import com.hcl.repository.GoalJpaRepository;

@Service
public class GoalsServiceImpl implements GoalsService {

	private GoalJpaRepository goalJpaRepository;

	@Autowired
	GoalsServiceImpl(GoalJpaRepository goalJpaRepository) {
		this.goalJpaRepository = goalJpaRepository;

	}

	@Override
	public List<Goals> getAllgoalsByYear(String year) {
		List<Goals> list = goalJpaRepository.getAllGoalsByYear(year);
		list.removeIf(Objects::isNull);
		return list;
	}

	@Override
	public List<Goals> getSpecificGoal(String level) {

		return goalJpaRepository.getSpecificGoal(level);
	}

	@Override
	public List<Goals> getSpecificduGoal(String level) {
		return goalJpaRepository.getSpecificduGoal(level);
	}

	@Override
	@Transactional
	public void updateOrSaveTheGoal(Goals goals) {
		goalJpaRepository.updateOrSaveTheGoal(goals.getParameter(), goals.getEffectiveUtil(),
				goals.getUnbilledInProjects(), goals.getInternalProjects(), goals.getBench(),
				goals.getDeliverySupport(), goals.getYear(), goals.getType());

	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	
		public List<Goals> selectUtilizationGoal(String goalYear){
			return goalJpaRepository.usp_wpc_GetGoals(goalYear);
		}

		public GoalsServiceImpl() {
			super();
		}

}
