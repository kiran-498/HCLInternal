package com.hcl.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.FinancialYears;
import com.hcl.entity.Goals;
import com.hcl.entity.ProjectBillSplit;
import com.hcl.entity.Utilization;
import com.hcl.pojo.ResultBean;
import com.hcl.repository.FinancialYearsRepository;
import com.hcl.repository.GoalJpaRepository;
import com.hcl.repository.ProjectBillSplitRepository;
import com.hcl.repository.UtilizationJdbcRepository;
import com.hcl.repository.UtilizationRepository;
import com.hcl.util.RequestMappingUrls;


@Service
public class UtilizationService {

	@Autowired 
	UtilizationRepository utilizationDao; 
	
	@Autowired
	ProjectBillSplitRepository repo_Bill_split;
	
	@Autowired
	private GoalJpaRepository repo_Goales;
	
	@Autowired
	private FinancialYearsRepository financialYearsRepository;
	@Autowired
	private GoalsServiceImpl goalserv;
	
	@Autowired 
	UtilizationJdbcRepository utilizationJdbcRepository; 
	
	// Varun Begins
	public String selectMaxDate(String tableName) {
		if (tableName.equals("Goal")) {
			return repo_Goales.FindMaxDate();
		} else {
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
	public Double sumutill(Utilization util) {
		return util.getBench()+util.getDelivery_Support()+util.getEffective_Utilization()+util.getInternal_Projects_SUT()+util.getUnbilled_In_Projects();}
	public Goals sumGoal(Goals ciscogoal,Double tot) {
		Float temper=(float) 0.0;
		DecimalFormat df=new DecimalFormat("#.##");
		Goals ciscogoalcopy=ciscogoal;
		Double goalTot=0.0;
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
			ciscogoalcopy.setBench(Double.valueOf(df.format((ciscogoalcopy.getBench()*100/goalTot))));
		}
		if(ciscogoalcopy.getDeliverySupport()!=null) {
			ciscogoalcopy.setDeliverySupport(Double.valueOf(df.format((ciscogoalcopy.getDeliverySupport()*100/goalTot))));
		}
		if(ciscogoalcopy.getEffectiveUtil()!=null) {
			ciscogoalcopy.setEffectiveUtil(Double.valueOf(df.format((ciscogoalcopy.getEffectiveUtil()*100/goalTot))));
		}
		if(ciscogoalcopy.getInternalProjects()!=null) {
			ciscogoalcopy.setInternalProjects(Double.valueOf(df.format((ciscogoalcopy.getInternalProjects()*100/goalTot))));
		} 
		if(ciscogoalcopy.getUnbilledInProjects()!=null) {
			ciscogoalcopy.setUnbilledInProjects(Double.valueOf(df.format((ciscogoalcopy.getUnbilledInProjects()*100/goalTot))));
			}
		return ciscogoalcopy;
	}
	
	
	public JSONObject duWise() throws JSONException {
		Date maxDateInUtil=Date.valueOf(selectMaxDate("Utilization"));
		String maxDateInGoal=selectMaxDate("Goal");
		List<String> distinctDu=selectDistinctDU(maxDateInUtil);
		distinctDu.add("Overall");
		List<Utilization> utilidata1=new ArrayList<Utilization>();
		List<Goals> Goalsdata1=new ArrayList<Goals>();
		if(maxDateInUtil!=null) {
		utilidata1=selectUtilization(Date.valueOf("2000-1-17"),maxDateInUtil);
		}
		if(maxDateInGoal!=null) {
		Goalsdata1=goalserv.selectUtilizationGoal(maxDateInGoal);
		}
		Date endDate=Date.valueOf("2020-7-21");
		Date startDate=Date.valueOf("2010-1-17");
		List<ProjectBillSplit> projectdata1=selectProjectBillSplit(startDate, endDate);
		JSONObject jsonobj=new JSONObject();
		HashMap<String, ResultBean> rbmap =new HashMap<String,ResultBean>(); 
	

			//rbmap.get(dus).setActual_Count(utileobj);
			Goals ciscoGoal=null,goal=null;
					for(Goals goalsobj:Goalsdata1) {
						if(goalsobj.getParameter().contains("DU")&& goalsobj.getType().equalsIgnoreCase("Overall")) {
							goal=goalsobj;
							break;	
						}
					}
					for(Goals goalsobj:Goalsdata1) {
						if(goalsobj.getParameter().contains("Cisco")&& goalsobj.getType().equalsIgnoreCase("Overall")) {
							ciscoGoal=goalsobj;
							break;	
						}
					}
					List<ResultBean> rb=new ArrayList<ResultBean>();
					for(Utilization utillobj:utilidata1) {
						
						Goals goalob;
						Double tot;
						try{
							tot=sumutill(utillobj);
							if(utillobj.getLevel().contains("Overall")) {
							goalob=sumGoal(ciscoGoal,tot);
							}
							else {
							goalob=sumGoal(goal,tot);
							}
							ResultBean tbbean=new ResultBean();
							tbbean.setActual_Count((Utilization)utillobj.clone());
							tbbean.setGoal_Count((Goals)goalob.clone());
							rb.add((ResultBean)tbbean.clone());
						
							}catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
			for(String dus:distinctDu) {
				for(ResultBean rbtemp:rb) {
					if(rbtemp.getActual_Count().getLevel().equalsIgnoreCase(dus)){
						rbmap.put(dus, rbtemp);
					}
				}
				for(ProjectBillSplit projectobj:projectdata1) {
					if(projectobj.getLevel().contains(dus)) {
						rbmap.get(dus).setProject_Bill_Split(projectobj);
						break;
					}
				}
			}
		jsonobj.put("DUWise",rbmap);
		
	return jsonobj;
	}
	
	//Varun Ends
	
	
	//Alekhya - Begin
	
	public Map<String,List<Utilization>> findAll(){;	
	Date MaxDate = utilizationJdbcRepository.FindMaxDate(RequestMappingUrls.UTILIZATION_FOR_MAX_DATE);
	List<Utilization> result = utilizationJdbcRepository.findAll(MaxDate) ;
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
		Double total = findTotal(eachRow);
		if(total != null) {
		um.setCalendar_Day(eachRow.getCalendar_Day());
		um.setLevel(eachRow.getLevel());
		um.setBench(roundValues(eachRow.getBench()*100/total));
		um.setDelivery_Support(roundValues(eachRow.getDelivery_Support()*100/total));
		um.setEffective_Utilization(roundValues(eachRow.getEffective_Utilization()*100/total));
		um.setInternal_Projects_SUT(roundValues(eachRow.getInternal_Projects_SUT()*100/total));
		um.setUnbilled_In_Projects(roundValues(eachRow.getUnbilled_In_Projects()*100/total));
		newResult.add(um);
		}
	}
	mainResult.put("DuWise",newResult);
	return mainResult;
}
/*
 * To find the total for each row, just once. If not, it calculated every time for every column of each row.
 */
public Double findTotal (Utilization eachRow) {
	Double Total = null;
	if(eachRow.getBench() != null && eachRow.getDelivery_Support() != null &&
	eachRow.getEffective_Utilization() != null && eachRow.getInternal_Projects_SUT()!= null && 
	eachRow.getUnbilled_In_Projects() != null) {
	Total = eachRow.getBench()+eachRow.getDelivery_Support()
	+eachRow.getEffective_Utilization()+eachRow.getInternal_Projects_SUT()+eachRow.getUnbilled_In_Projects();
	}
	return Total;
}

/*
 * For Rounding of the returned Percent values to 2 decimals. 
 */
public Double roundValues(Double input) {
	BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
	return bd.doubleValue();
}

public List<FinancialYears> findFinancialYears(String year) {
	return financialYearsRepository.findFinancialYears();
}
 // Alekhya Ends
}
