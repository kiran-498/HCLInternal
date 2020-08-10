package com.hcl.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hcl.entity.UnBilledTracker;
import com.hcl.util.ConstantUtility;

public class UnbilledRowMapper implements RowMapper<UnBilledTracker> {

	@Override
	public UnBilledTracker mapRow(ResultSet rs, int rowNum) throws SQLException {

		UnBilledTracker unBilledDetails = new UnBilledTracker();
		unBilledDetails.setEmployeeCode(rs.getFloat(ConstantUtility.Employee_Code));
		unBilledDetails.setEmployeeName(rs.getString(ConstantUtility.Employee_Name));
		unBilledDetails.setAdjustedDU(rs.getString(ConstantUtility.Adjusted_DU));
		unBilledDetails.setCategoryType(rs.getString(ConstantUtility.Category_Type));
		unBilledDetails.setPlan(rs.getString(ConstantUtility.Plan));
		unBilledDetails.setPlan_Change_Count(rs.getInt(ConstantUtility.plan_Change_Count));
		unBilledDetails.setUnbillAgeing(rs.getInt(ConstantUtility.Unbill_Ageing));
		unBilledDetails.seteBDLWDTRFOutDate(rs.getDate(ConstantUtility.EBD_LWD_TRF_Out_Date));
		unBilledDetails.setRemarks(rs.getString(ConstantUtility.Remarks));
		unBilledDetails.setLastUpdated(rs.getDate(ConstantUtility.Last_Updated));
		return unBilledDetails;
	}

}
