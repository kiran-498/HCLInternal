package com.hcl.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Goals;
import com.hcl.util.ConstantUtility;

@Repository
public class GoalJdbcRepositoryImpl implements GoalJdbcRepository {

	private DataSource dataSource;
	@Autowired
	public GoalJdbcRepositoryImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public List<Goals> getAllGoalsByYear(String year) {
		List<Goals> result = new ArrayList<Goals>();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(ConstantUtility.GET_GOAL_PROCEDURE_NAME);
		SqlParameterSource in = new MapSqlParameterSource().addValue(ConstantUtility.GET_GOAL_PROCEDURE_PARAMETER, year);
		Map<String, Object> out = jdbcCall.execute(in);
		for(Entry<String, Object> row:out.entrySet()){
		result.addAll((Collection<? extends Goals>) row.getValue());
		}
		return result;
	}

	@Override
	public List<Goals> getSpecificGoal(String parameter) {
		List<Goals> result = new ArrayList<Goals>();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_NAME);
		SqlParameterSource in = new MapSqlParameterSource().addValue(ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_PARAMETER, parameter);
		Map<String, Object> out = jdbcCall.execute(in);
		for(Entry<String, Object> row:out.entrySet()){
		result.addAll((Collection<? extends Goals>) row.getValue());
		}
		return result;
	}

	@Override
	public List<Goals> getSpecificduGoal(String level) {
		List<Goals> result = new ArrayList<Goals>();
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_NAME);
		SqlParameterSource in = new MapSqlParameterSource().addValue(ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_PARAMETER, level);
		Map<String, Object> out = jdbcCall.execute(in);
		for(Entry<String, Object> row:out.entrySet()){
		result.addAll((Collection<? extends Goals>) row.getValue());
		}
		return result;
	}

	@Override
	public void updateOrSaveTheGoal(Goals goals) {
			Map map = new HashMap<>();
			map.put(ConstantUtility.PARAMETER_TYPE, goals.getParameter());
			map.put(ConstantUtility.EFFECTIVEUTIL_TYPE, goals.getEffectiveUtil());
			map.put(ConstantUtility.UNBILLED_IN_PROJECTS_TYPE, goals.getUnbilledInProjects());
			map.put(ConstantUtility.INTERNAL_PROJECTS_TYPE, goals.getInternalProjects());
			map.put(ConstantUtility.BENCH_TYPE, goals.getBench());
			map.put(ConstantUtility.DELIVERY_SUPPORT_TYPE, goals.getDeliverySupport());
			map.put(ConstantUtility.YEAR_TYPE, goals.getYear());
			map.put(ConstantUtility.TYPE_TYPE, goals.getType());
			
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(ConstantUtility.EDIT_GOAL_PROCEDURE_NAME);
		SqlParameterSource in = new MapSqlParameterSource().addValues(map);
		Map<String, Object> out = jdbcCall.execute(in);
		System.out.println(out);
		
	}
	
	
	

}
