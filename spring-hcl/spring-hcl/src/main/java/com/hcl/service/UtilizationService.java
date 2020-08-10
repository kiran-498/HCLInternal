package com.hcl.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.FinancialYears;
import com.hcl.entity.Goals;
import com.hcl.entity.ProjectBillSplit;
import com.hcl.entity.Utilization;
import com.hcl.repository.FinancialYearsRepository;
import com.hcl.repository.GoalJpaRepository;
import com.hcl.repository.Repo_Project_bill_split;
import com.hcl.repository.Repo_Utilization;


@Service
public class UtilizationService {

	@Autowired 
	Repo_Utilization utilizationDao;
	
	@Autowired
	Repo_Project_bill_split repo_Bill_split;
	
	@Autowired
	private GoalJpaRepository repo_Goles;
	
	@Autowired
	private FinancialYearsRepository financialYearsRepository;
	
	// Varun Begins
			Date calDate;
			public String selectMaxDate(String tableName) {
				if(tableName.equals("Goal")){
						return repo_Goles.FindMaxDate();
				}
				else{
					return utilizationDao.FindMaxDate().toString();
				}
			}
	public List<String> selectDistinctDU(Date caldate) {
		return utilizationDao.FindDUListOrderByLevelAsc(caldate);
	}
	public List<Utilization> selectUtilization(Date startDate,Date endDate){
		return utilizationDao.FindRecords(startDate, endDate);
	}
	public List<ProjectBillSplit> selectProjectBillSplit(Date startDate,Date endDate){
		return repo_Bill_split.FindRecords(startDate, endDate);
	}
	public float sumutill(Utilization util) {
		return util.getBench()+util.getDelivery_Support()+util.getEffective_Utilization()+util.getInternal_Projects_SUT()+util.getUnbilled_In_Projects();}
	public Goals sumGoal(Goals ciscogoal,Float tot) {
		Float temper=(float) 0.0;
		DecimalFormat df=new DecimalFormat("#.##");
		Goals ciscogoalcopy=ciscogoal;
		Float goalTot=(float)0;
		if(ciscogoalcopy.getBench()!=null) {
			goalTot=goalTot+ciscogoalcopy.getBench();
			ciscogoalcopy.setBench(((ciscogoalcopy.getBench()*tot)/100));
		}
		if(ciscogoalcopy.getDeliverySupport()!=null) {
			goalTot=goalTot+ciscogoalcopy.getDeliverySupport();
			ciscogoalcopy.setDeliverySupport((ciscogoalcopy.getDeliverySupport()*tot)/100);
		}
		if(ciscogoalcopy.getEffectiveUtil()!=null) {
			goalTot=goalTot+ciscogoalcopy.getEffectiveUtil();
			ciscogoalcopy.setEffectiveUtil((ciscogoalcopy.getEffectiveUtil()*tot)/100);
		}
		if(ciscogoalcopy.getInternalProjects()!=null) {
			goalTot=goalTot+ciscogoalcopy.getInternalProjects();
			ciscogoalcopy.setInternalProjects((ciscogoalcopy.getInternalProjects()*tot)/100);
		} 
		if(ciscogoalcopy.getUnbilledInProjects()!=null) {
			goalTot=goalTot+ciscogoalcopy.getUnbilledInProjects();
			ciscogoalcopy.setUnbilledInProjects((ciscogoalcopy.getUnbilledInProjects()*tot)/100);
			}
//percentage cal
		if(ciscogoalcopy.getBench()!=null) {
			ciscogoalcopy.setBench(Float.valueOf(df.format((ciscogoalcopy.getBench()*100/goalTot))));
		}
		if(ciscogoalcopy.getDeliverySupport()!=null) {
			ciscogoalcopy.setDeliverySupport(Float.valueOf(df.format((ciscogoalcopy.getDeliverySupport()*100/goalTot))));
		}
		if(ciscogoalcopy.getEffectiveUtil()!=null) {
			ciscogoalcopy.setEffectiveUtil(Float.valueOf(df.format((ciscogoalcopy.getEffectiveUtil()*100/goalTot))));
		}
		if(ciscogoalcopy.getInternalProjects()!=null) {
			ciscogoalcopy.setInternalProjects(Float.valueOf(df.format((ciscogoalcopy.getInternalProjects()*100/goalTot))));
		} 
		if(ciscogoalcopy.getUnbilledInProjects()!=null) {
			ciscogoalcopy.setUnbilledInProjects(Float.valueOf(df.format((ciscogoalcopy.getUnbilledInProjects()*100/goalTot))));
			}
		return ciscogoalcopy;
	}
	//Varun Ends
	
	
	//Alekhya - Begin
	
	public Map<String,List<Utilization>> findAll(Date MaxDate){;	
		List<Utilization> result = utilizationDao.findAll(MaxDate) ;
		Map<String,List<Utilization>> result1 = findPercentages(result);
		return result1;
	}
	
	/*
	 * To find the percentage values for the columns required. 
	 */
	public Map<String,List<Utilization>> findPercentages(List<Utilization> result) {
		Map<String,List<Utilization>> mainResult = new HashMap<String,List<Utilization>>();
		List<Utilization> newResult = new ArrayList<Utilization>();
		Utilization um = null;
		for (Utilization eachRow : result) {
			um = new Utilization();
			double total = findTotal(eachRow);
			um.setCalendar_Day(eachRow.getCalendar_Day());
			um.setLevel(eachRow.getLevel());
			um.setBench(roundValues(eachRow.getBench()*100/total));
			um.setDelivery_Support(roundValues(eachRow.getDelivery_Support()*100/total));
			um.setEffective_Utilization(roundValues(eachRow.getEffective_Utilization()*100/total));
			um.setInternal_Projects_SUT(roundValues(eachRow.getInternal_Projects_SUT()*100/total));
			um.setUnbilled_In_Projects(roundValues(eachRow.getUnbilled_In_Projects()*100/total));
			newResult.add(um);
		}
		mainResult.put("DuWise",newResult);
		return mainResult;
	}
	/*
	 * To find the total for each row, just once. If not, it calculated every time for every column of each row.
	 */
	public double findTotal (Utilization eachRow) {
		return (eachRow.getBench()+eachRow.getDelivery_Support()
		+eachRow.getEffective_Utilization()+eachRow.getInternal_Projects_SUT()+eachRow.getUnbilled_In_Projects());
	}
	
	/*
	 * For Rounding of the returned Percent values to 2 decimals. 
	 */
	public float roundValues(double input) {
		BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
		return (float)bd.doubleValue();
	}
	
	public List<FinancialYears> findFinancialYears(String fy) {
		return financialYearsRepository.findFinancialYears();
	}
 // Alekhya Ends
}
