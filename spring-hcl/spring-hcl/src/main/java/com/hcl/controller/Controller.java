package com.hcl.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.entity.FinancialYears;
import com.hcl.entity.Goals;
import com.hcl.entity.ProjectBillSplit;
import com.hcl.entity.UnBilledTracker;
import com.hcl.entity.Utilization;
import com.hcl.exception.BadRequestException;
import com.hcl.pojo.ResultBean;
import com.hcl.service.GoalsService;
import com.hcl.service.UnBilledTrackerService;
import com.hcl.service.UtilizationService;
import com.hcl.util.GateWayResponse;
import com.hcl.util.MessageKeysUtility;
import com.hcl.util.RequestMappingUrls;

@RestController
@RequestMapping(RequestMappingUrls.CONTROLLER_CLASS_LEVEL_URL)
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	UtilizationService utilizationService;
	private GoalsService goalsService;
	private UnBilledTrackerService unBilledTrackerService;

	@Autowired
	public Controller(UnBilledTrackerService unBilledTrackerService, GoalsService goalsService) {

		this.unBilledTrackerService = unBilledTrackerService;
		this.goalsService = goalsService;
	}

	//@GetMapping(RequestMappingUrls.UTILIZATION_URL + RequestMappingUrls.GOAL_URL + RequestMappingUrls.GOAL_YEAR_PARAM)
	public GateWayResponse<?> getAllgols(@PathVariable String year) {

		try {
			List<Goals> listOfGoals = goalsService.getAllgoalsByYear(year);
			return new GateWayResponse<>(HttpStatus.OK, listOfGoals, MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}
	}

	@GetMapping(RequestMappingUrls.UTILIZATION_URL + RequestMappingUrls.SPECIFIC_GOAL_URL
			+ RequestMappingUrls.SPECIFIC_GOAL_LEVEL_PARAM)
	public GateWayResponse<?> getSpecificGoal(@PathVariable String level) {

		try {

			// logger.info("values are :" +level);
			List<Goals> listOfGoals = goalsService.getSpecificGoal(level);
			return new GateWayResponse<>(HttpStatus.OK, listOfGoals, MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}
	}

	@GetMapping(RequestMappingUrls.UTILIZATION_URL + RequestMappingUrls.SPECIFIC_DUGOAL_URL
			+ RequestMappingUrls.SPECIFIC_GOAL_LEVEL_PARAM)
	public GateWayResponse<?> getSpecificduGoal(@PathVariable String level) {

		try {
			List<Goals> listOfGoals = goalsService.getSpecificduGoal(level);
			return new GateWayResponse<>(HttpStatus.OK, listOfGoals, MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}
	}

	@PostMapping(RequestMappingUrls.UTILIZATION_URL + RequestMappingUrls.GOAL_URL + RequestMappingUrls.UPDATE_URL)
	public GateWayResponse<?> updateOrSaveTheGoal(@RequestBody Goals goals) {

		try {
			goalsService.updateOrSaveTheGoal(goals);
			return new GateWayResponse<>(HttpStatus.OK, MessageKeysUtility.MESSAGE_SUCCESS,
					MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}
	}

	@GetMapping(RequestMappingUrls.UNBILLED_URL)
	public GateWayResponse<?> getUnBilledDetails() {

		try {
			Map<String, Map<String, Map<String, Integer>>> unBilledDetails = unBilledTrackerService
					.getUnBilledDetails();
			return new GateWayResponse<>(HttpStatus.OK, unBilledDetails, MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}

	}

	@GetMapping(RequestMappingUrls.UNBILLED4_DATA_URL)
	public GateWayResponse<?> getUnBilled4Data() {

		try {
			Map<String, Map<String, Map<String, Integer>>> unBilledDetails = unBilledTrackerService.getUnBilled4Data();
			return new GateWayResponse<>(HttpStatus.OK, unBilledDetails, MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}

	}

	@GetMapping(RequestMappingUrls.UNBILLED_URL + RequestMappingUrls.UNBILLED3_PLAN_DATA_URL
			+ RequestMappingUrls.UNBILLED3_PLAN_TYPE_PARAM)
	public GateWayResponse<?> getUnbilledL3PlanData(@PathVariable String type, @PathVariable String column1,
			@PathVariable String column) {
		try {
			List<JSONObject> unBilledDetails = unBilledTrackerService.getUnbilledL3PlanData(type, column1, column);
			return new GateWayResponse<>(HttpStatus.OK, unBilledDetails, MessageKeysUtility.MESSAGE_SUCCESS);
		} catch (NullPointerException nullExc) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
		} catch (BadRequestException badRequestException) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
		} catch (Exception e) {
			return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	// To retrieve the data from the utilization table when the MaxDate is given
		/*
		 * URL : http://localhost:8080/wpc/utilization
		 * Output : JSON Format {"DUWise":[Objects]}
		 * For now, the Max date is taken, small change in the query gives the range between 2 dates and data gets extracted like before. 
		 */
		@GetMapping("/utilization")
		public Map<String,List<com.hcl.entity.Utilization>> findAll(){
			Date maxDateInUtil=Date.valueOf(utilizationService.selectMaxDate("Utilization"));
			return utilizationService.findAll(maxDateInUtil);
		}
		
		/*
		 * To retrieve the financial years list. 
		 * URL : http://localhost:8080/wpc//utilization/goal?FY=FY-21
		 * Output : [Objects] 
		 * For now, it returns all the data. Small change in the query gives the data for the given financial year.
		 */
		@GetMapping("/utilization/goal/{FY}")
		public List<FinancialYears> findFinancialYears(@PathVariable("FY") String fy){
		return utilizationService.findFinancialYears(fy);
		
		}
		
		//To retrieve the data with Category Type and Plan		
		/*
		 * URL for Testing : http://localhost:8080/wpc/unbilled/unbilledL3OverallData?Category_Type=M-RAMP UP-D2&Plan=Bill
		 * Output : JSon Format {"Data":[Objects]}
		 */
		@GetMapping("/unbilled/unbilledL3OverallData/{Category_Type}/{Plan}")
		public Map<String,List<UnBilledTracker>> findWithCatAndPlan(@PathVariable("Category_Type")String CategoryType , @PathVariable("Plan")String plan){	
			return unBilledTrackerService.findWithCatAndPlan(CategoryType,plan);
		}
		
		//To retrieve the data with Category Type and Age period.
		/*
		 * Url for Testing : http://localhost:8080/wpc/unbilled/unbilledL3AgingData?Category_Type=M-RAMP UP-D2&Age=60-90Days
		 *  output : JSON Format {"Data":[Objects]}
		 */
		@GetMapping("/unbilled/unbilledL3AgingData/{Category_Type}/{Age}")
		public Map<String,List<UnBilledTracker>> findWithCatAndAge(@PathVariable("Category_Type")String CategoryType , @PathVariable("Age")String age){	
			Map<String,List<UnBilledTracker>> JsonResult = new HashMap<>(); 
			//List<UnbilledTracker> result =  unbilledTrackerService.FindByCatAndAge(CategoryType,age);
			JsonResult.put("Data", unBilledTrackerService.FindByCatAndAge(CategoryType,age));
			return JsonResult;
		}
		
		//Varun Begins
			
			@RequestMapping("/wpc/utilization/duwise")
			public JSONObject printall() throws JSONException {
				Date maxDateInUtil=Date.valueOf(utilizationService.selectMaxDate("utill"));
				String maxDateInGoal=utilizationService.selectMaxDate("Goal");
				List<String> distinctDu=utilizationService.selectDistinctDU(maxDateInUtil);
				distinctDu.add("Overall");
				List<Utilization> utilidata1=utilizationService.selectUtilization(Date.valueOf("2010-1-17"),maxDateInUtil);
				List<Goals> Goalsdata1=goalsService.selectUtilizationGoal(maxDateInGoal);
				Date endDate=Date.valueOf("2020-7-21");
				Date startDate=Date.valueOf("2010-1-17");
				List<ProjectBillSplit> projectdata1=utilizationService.selectProjectBillSplit(startDate, endDate);
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
								Float tot;
								try{
									tot=utilizationService.sumutill(utillobj);
									if(utillobj.getLevel().contains("Overall")) {
									goalob=utilizationService.sumGoal(ciscoGoal,tot);
									}
									else {
									goalob=utilizationService.sumGoal(goal,tot);
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
			// task 2 END
			
			// remaining tasks
			
			@RequestMapping("/wpc/unbilled/unbilledL4OverallData/{Du}/{Plan}")
			public List<JSONObject> unbilledL4OverallData(@PathVariable("Du") String Du,@PathVariable("Plan") String plan) {
				List<JSONObject> result=new ArrayList<JSONObject>();
				for(UnBilledTracker x:unBilledTrackerService.FindByPlanAndDu(Du, plan)) {
					result.add(x.unBilledL4PlanDataWithOutMonthAndYear());
				}
				return result;
			}
			@RequestMapping("/wpc/unbilled/unbilledL4AgingData/{Du}/{Age}")
			public List<JSONObject> unbilledL4AgingData(@PathVariable("Du") String Du,@PathVariable("Age") String Age) {
				List<JSONObject> result=new ArrayList<JSONObject>();
				for(UnBilledTracker x:unBilledTrackerService.FindByDuAndAge(Du, Age)) {
					result.add(x.unBilledL4PlanDataWithOutMonthAndYear());
				}
				
				return result;
			}
			@RequestMapping("/wpc/unbilled/unbilledL4PlanData/{Du}/{month}/{year}")
			
			public List<JSONObject> unbilledL4PlanData(@PathVariable("Du") String Du,@PathVariable("month") String month,@PathVariable("year") String year) {
				List<JSONObject> result=new ArrayList<JSONObject>();
				if(month.contains("Grand Total")) {
				for(UnBilledTracker x:unBilledTrackerService.FindByDuAndYearAndMonth(Du,month,year)) {
					result.add(x.unBilledL4PlanDataWithMonthAndYear());
				}}
				else {
					for(UnBilledTracker x:unBilledTrackerService.FindByDuAndYearAndMonth(Du,month,year)) {
						result.add(x.unBilledL4PlanDataWithOutMonthAndYear());
					}			
				}
					
				
				return result;
			}

}
