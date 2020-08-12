package com.hcl.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ConstantUtility {

	public static final String MONTH_ANDYEARFORMAT = "MMM/yyyy";
	/*public static final String BILL_CATEGORY = "Bill";
	public static final String NO_PLAN_CATEGORY = "No Plan";
	public static final String OK_TO_RELEASE_CATEGORY = "Okay to Release";*/
	public static final String LESS_THAN_30 = "< 30 days";
	public static final String BETWEEN_30_60 = "30-60 days";
	public static final String BETWEEN_60_90 = "60-90-Days";
	public static final String GRATER_THAN_90 = "> 90-Days";
	public static final String TABLE1 = "Table1";
	public static final String TABLE2 = "Table2";
	public static final String TABLE3 = "Table3";
	public static final String GET_GOAL_PROCEDURE_NAME = "usp_wpc_GetGoals";
	public static final String GET_GOAL_PROCEDURE_PARAMETER = "Year";
	public static final String GET_SPICIFIC_GOAL_PROCEDURE_PARAMETER = "Parameter";
	public static final String PARAMETER_TYPE = "Parameter";
	public static final String EFFECTIVEUTIL_TYPE = "EffectiveUtil";
	public static final String UNBILLED_IN_PROJECTS_TYPE = "UnbilledInProjects";
	public static final String INTERNAL_PROJECTS_TYPE = "InternalProjects";
	public static final String BENCH_TYPE = "Bench";
	public static final String DELIVERY_SUPPORT_TYPE = "DeliverySupport";
	public static final String YEAR_TYPE = "Year";
	public static final String TYPE_TYPE = "Type";
	public static final String SELECT_UN_BILLED = "select * from UnBilled_Tracker where EBD_LWD_TRF_Out_Date >=?";
	public static final String Employee_Code = "Employee_Code";
	public static final String Employee_Name = "Employee_Name";
	public static final String Adjusted_DU = "Adjusted_DU";
	public static final String Category_Type = "Category_Type";
	public static final String Plan = "Plan";
	public static final String plan_Change_Count = "plan_Change_Count";
	public static final String Unbill_Ageing = "Unbill_Ageing";
	public static final String EBD_LWD_TRF_Out_Date = "EBD_LWD_TRF_Out_Date";
	public static final String Remarks = "Remarks";
	public static final String Last_Updated = "Last_Updated";
	public static final String DUTYPE = "duType";
	public static final String GRAND_TOTAL = "Grand Total";
	public static final String SELECT_UN_BILLED_CATEGORY_TYPE = "select * from UnBilled_Tracker where Category_Type =?";
	public static final String SELECT_UN_BILLED_CATEGORY_TYPE_AND_DATE = "select * from UnBilled_Tracker where EBD_LWD_TRF_Out_Date =? and EBD_LWD_TRF_Out_Date >=?";

	public static final String EDIT_GOAL_PROCEDURE_NAME = "usp_wpc_EditGoals";
	public static final String GET_SPICIFIC_GOAL_PROCEDURE_NAME = "usp_wpc_GetSpecificGoals";//"{call usp_wpc_GetSpecificGoals(:Parameter)}"
	public static final  int zero=0;
	public static final  String empty="";
	public static final String SELECT_UN_BILLED_CATEGORY_TYPE_MONTH_YEAR = "select * from UnBilled_Tracker where Category_Type = ? and DATEPART(YYYY,EBD_LWD_TRF_Out_Date)=? and DATEPART(MONTH,EBD_LWD_TRF_Out_Date)=? ";
	public static final String SELECT_UN_BILLED_CATEGORY_TYPE_YEAR = "select * from UnBilled_Tracker where Category_Type = ? and DATEPART(YYYY,EBD_LWD_TRF_Out_Date)=?";

	public static String getMontAndYear(Date date) {
		SimpleDateFormat simpleMonthFormat = new SimpleDateFormat(ConstantUtility.MONTH_ANDYEARFORMAT);
		String monthAndYear;
		monthAndYear = simpleMonthFormat.format(date);
		return monthAndYear;
	}

	public static Integer getDaysInMonth(String month) {
		switch (month) {
		case "Jan":
			return 1;
		case "Feb":
			return 2;
		case "Mar":
			return 3;
		case "Apr":
			return 4;
		case "May":
			return 5;
		case "Jun":
			return 6;
		case "Jul":
			return 7;
		case "Aug":
			return 8;
		case "Sep":
			return 9;
		case "Oct":
			return 10;
		case "Nov":
			return 11;
		case "Dec":
			return 12;
		default:
			throw new IllegalArgumentException("Invalid Month");
		}
	}

}
