package com.hcl.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import com.hcl.entity.FinancialYears;

@Repository
public class FinancialYearsRepositoryImpl implements  FinancialYearsRepository{
	
	@Autowired
	JdbcTemplate JdbcTemplate;

	@Override
	public List<FinancialYears> findFinancialYears() {
		String sql = "select * from currentFY";
		List<Map<String, Object>> listOfFY = JdbcTemplate.queryForList(sql);
		List<FinancialYears> result = new ArrayList<FinancialYears>();
		for(Map<String, Object> eachOfFY:listOfFY) {
			FinancialYears financialYears= new FinancialYears();
			financialYears.setYears(eachOfFY.get("Years").toString());
			financialYears.setFy(eachOfFY.get("FY").toString());
			result.add(financialYears);
		}
		return result;
	}

}
