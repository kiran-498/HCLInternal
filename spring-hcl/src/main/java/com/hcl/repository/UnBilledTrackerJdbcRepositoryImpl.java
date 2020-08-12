package com.hcl.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.entity.UnBilledTracker;
import com.hcl.service.UnbilledRowMapper;
import com.hcl.util.ConstantUtility;

@Repository
public class UnBilledTrackerJdbcRepositoryImpl implements UnBilledTrackerJdbcRepository {

	private JdbcTemplate JdbcTemplate;
	@Autowired
	public UnBilledTrackerJdbcRepositoryImpl(JdbcTemplate JdbcTemplate) {
		this.JdbcTemplate=JdbcTemplate;
	}
	
	@Override
	public List<UnBilledTracker> getEBDLWDTRFOutDateGreaterThanEqual(Date date) {
		List<Map<String, Object>> listOfFY = JdbcTemplate.queryForList(ConstantUtility.SELECT_UN_BILLED,new Object[]{date}) ;
		List<UnBilledTracker> list = mapData(listOfFY);
		
		return list;
	}

	private List<UnBilledTracker> mapData(List<Map<String, Object>> listOfFY) {
		List<UnBilledTracker> list =  new ArrayList<>();
		for (Map<String, Object> rs : listOfFY) {
			UnBilledTracker unBilledDetails = new UnBilledTracker();
			unBilledDetails.setEmployeeCode((Double)rs.get(ConstantUtility.Employee_Code));
			unBilledDetails.setEmployeeName((String)rs.get(ConstantUtility.Employee_Name));
			unBilledDetails.setAdjustedDU((String)rs.get(ConstantUtility.Adjusted_DU));
			unBilledDetails.setCategoryType((String)rs.get(ConstantUtility.Category_Type));
			unBilledDetails.setPlan((String)rs.get(ConstantUtility.Plan));
			unBilledDetails.setPlan_Change_Count((Integer)rs.get(ConstantUtility.plan_Change_Count));
			unBilledDetails.setUnbillAgeing((Integer)rs.get(ConstantUtility.Unbill_Ageing));
			unBilledDetails.seteBDLWDTRFOutDate((Date)rs.get(ConstantUtility.EBD_LWD_TRF_Out_Date));
			unBilledDetails.setRemarks((String)rs.get(ConstantUtility.Remarks));
			list.add(unBilledDetails);
		}
		return list;
	}

	@Override
	public List<UnBilledTracker> findAllByCategoryType(String categoryType) {
		List<Map<String, Object>> listOfFY = JdbcTemplate.queryForList(ConstantUtility.SELECT_UN_BILLED_CATEGORY_TYPE,new Object[]{categoryType}) ;
		List<UnBilledTracker> list = mapData(listOfFY);
		return list;
	}

	@Override
	public List<UnBilledTracker> findAllByCategoryTypeAndMonthAndYear(String categoryType, int year, int month) {
		//SELECT_UN_BILLED_CATEGORY_TYPE_MONTH_YEAR
		List<Map<String, Object>> listOfFY = JdbcTemplate.queryForList(ConstantUtility.SELECT_UN_BILLED_CATEGORY_TYPE_MONTH_YEAR,new Object[]{categoryType,year,month}) ;
		List<UnBilledTracker> list = mapData(listOfFY);
		return list;
	}

	@Override
	public List<UnBilledTracker> findAllByCategoryTypeAndYear(String categoryType, int year) {
		List<Map<String, Object>> listOfFY = JdbcTemplate.queryForList(ConstantUtility.SELECT_UN_BILLED_CATEGORY_TYPE_YEAR,new Object[]{categoryType,year}) ;
		List<UnBilledTracker> list = mapData(listOfFY);
		return list;
	}
	
	@Override
	public List<UnBilledTracker> findWithCatAndPlan(String categoryType, String plan) {
		LocalDate date = LocalDate.now().of(LocalDate.now().getYear(),LocalDate.now().getMonth(), 01);
		String sql = "select * from UnBilled_Tracker "
		+ "where Category_Type =? AND [Plan] =? AND EBD_LWD_TRF_Out_Date >= ?";
		List<UnBilledTracker> out = JdbcTemplate.query(sql,  new Object[] {categoryType,plan,date},new UnbilledRowMapper());
		return out;	
	}

	@Override
	public List<UnBilledTracker> findWithCatAndAge(String categoryType, String age) {
		LocalDate date = LocalDate.now().of(LocalDate.now().getYear(),LocalDate.now().getMonth(), 01);
		String sql = "select * from UnBilled_Tracker "
		+ "where Category_Type =? AND EBD_LWD_TRF_Out_Date >= ? AND ";
		if(age.equals("<30Days")) {
			sql = sql+ "[Unbill_Ageing] <= 30";
		}
		else if(age.equals("30-60Days")) {
			sql = sql+ "[Unbill_Ageing] > 30 and [Unbill_Ageing] <= 60";
		}
		else if(age.equals("60-90Days")) {
			sql = sql+ "[Unbill_Ageing] > 60 and [Unbill_Ageing] <= 90";
		}
		else if(age.equals(">90Days")) {
			sql = sql+ "[Unbill_Ageing] > 90";
		}
			
		List<UnBilledTracker> out = JdbcTemplate.query(sql,  new Object[] {categoryType,date},new UnbilledRowMapper());
		return out;
	}

}
