package com.hcl.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Utilization;

@Repository
public class UtilizationJdbcRepositoryImpl implements UtilizationJdbcRepository{

	@Autowired 
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate JdbcTemplate;
	
	@Override
	public List<Utilization> findAll(Date MaxDate) {
		String sql = "SELECT * FROM dbo.Utilization WHERE Calendar_Day ='"+MaxDate+"'";
		List<Map<String, Object>> rows = JdbcTemplate.queryForList(sql);
		List<Utilization> result = new ArrayList<Utilization>();
		for(Map<String, Object> row:rows){
			Utilization um = new Utilization();
			um.setCalendar_Day(MaxDate);
			um.setLevel((String)row.get("Level"));
			um.setEffective_Utilization((Double)row.get("Effective_Utilization"));
			um.setUnbilled_In_Projects((Double)row.get("Unbilled_In_Projects"));
			um.setInternal_Projects_SUT((Double)row.get("Internal_Projects_SUT"));
			um.setBench((Double)row.get("Bench"));
			um.setDelivery_Support((Double)row.get("Delivery_Support"));
			result.add(um);
			}
		return result;
	}
	
	/*
	 * To retireve the Max date for the Query. This can be used as a common function for other requirements. 
	 */

	@Override
	public Date FindMaxDate(String TableName) {
		String sql= "select MAX(Calendar_Day) from "+TableName;
		Date forMaxDate = JdbcTemplate.queryForObject(sql, Date.class);
	return forMaxDate ;
	}
}
