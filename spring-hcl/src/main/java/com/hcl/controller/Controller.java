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

/**
 * @author sai
 *
 */
@RestController
@RequestMapping(RequestMappingUrls.CONTROLLER_CLASS_LEVEL_URL)
public class Controller {
	//Sai Beigns
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	private UtilizationService utilizationService;
	private GoalsService goalsService;
	private UnBilledTrackerService unBilledTrackerService;

	@Autowired
	public Controller(UnBilledTrackerService unBilledTrackerService, GoalsService goalsService,UtilizationService utilizationService) {

		this.unBilledTrackerService = unBilledTrackerService;
		this.goalsService = goalsService;
		this.utilizationService=utilizationService;
	}


	/**
	 * http://localhost:9090/wpc/utilization/goal/FY-18/Onsite
	 * @param year
	 * @param type
	 * @return list of Goals
	 */
	@GetMapping(RequestMappingUrls.UTILIZATION_URL + RequestMappingUrls.GOAL_URL + RequestMappingUrls.GOAL_YEAR_PARAM
			+ RequestMappingUrls.GOAL_TYPE_PARAM)
	public GateWayResponse<?> getAllgols(@PathVariable String year,@PathVariable String type) {

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

	/**
	 * http://localhost:9090/wpc/utilization/specificGoal/Overall
	 * @param level
	 * @return list of Goals
	 */
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

	/**
	 * http://localhost:9090/wpc/utilization/duGoal/ERS-HnC-DU
	 * @param level
	 * @return list of Goals
	 */
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

	/**
	 * http://localhost:9090/wpc/utilization/goal/update
	 * @param Goals
	 * @return sucess string
	 */
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
	/**
	 * http://localhost:9090/wpc/unbilled
	 * @return Map
	**/
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

	/**
	 * http://localhost:9090/wpc/unbilledL4Data
	 * @return Map
	**/
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

	/**
	 * http://localhost:9090/wpc/unbilled/unbilledL3PlanData/M-RAMP UP-D2/Grand Total/Grand Total
	 * @param type
	 *@param month
	 *@param year
	 * @return JSONObject
	 */
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
	
	//Sai Ends
	//Alekhya Begins
	// To retrieve the data from the utilization table when the MaxDate is given
			/*
			 * URL : http://localhost:8080/wpc/utilization
			 * Output : JSON Format {"DUWise":[Objects]}
			 * For now, the Max date is taken, small change in the query gives the range between 2 dates and data gets extracted like before. 
			 */
			@GetMapping(RequestMappingUrls.UTILIZATION_URL)
			public GateWayResponse<?> findAll(){
				try {
				//Date maxDateInUtil=Date.valueOf(utilizationService.selectMaxDate(RequestMappingUrls.UTILIZATION_FOR_MAX_DATE));
				Map<String,List<Utilization>> findAllDataWithMaxDate = utilizationService.findAll();
				return new GateWayResponse<>(HttpStatus.OK, findAllDataWithMaxDate, MessageKeysUtility.MESSAGE_SUCCESS);
				} catch (NullPointerException nullExc) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
				} catch (BadRequestException badRequestException) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
				} catch (Exception e) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
				}
			}
			
			//To retrieve the data with Category Type and Plan		
			/*
			 * URL to use : http://localhost:8080/wpc/unbilled/unbilledL3OverallData/{Category_Type}/{Plan}
			 * URL for Testing : http://localhost:8080/wpc/unbilled/unbilledL3OverallData/M-RAMP UP-D2/Bill
			 * Output : JSon Format {"Data":[Objects]}
			 */
			@GetMapping(RequestMappingUrls.UNBILLED_URL+RequestMappingUrls.UNBILLEDL3_OVERALL_DATA + 
					RequestMappingUrls.CATEGORY_TYPE + RequestMappingUrls.PLAN)
			public GateWayResponse<?> findWithCatAndPlan(@PathVariable String Category_Type , @PathVariable String plan){	
				try {
				Map<String,List<UnBilledTracker>> ListOfDataWithCatAndPlan = unBilledTrackerService.findWithCatAndPlan(Category_Type,plan);
				return new GateWayResponse<>(HttpStatus.OK, ListOfDataWithCatAndPlan, MessageKeysUtility.MESSAGE_SUCCESS);
			} catch (NullPointerException nullExc) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
			} catch (BadRequestException badRequestException) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
			} catch (Exception e) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
			}
			}
			
			//To retrieve the data with Category Type and Age period.
			/*
			 * Url to use : http://localhost:8080/wpc/unbilled/unbilledL3AgingData/{Category_Type}/{Age}
			 * Url for Testing : http://localhost:8080/wpc/unbilled/unbilledL3AgingData/M-RAMP UP-D2/60-90Days
			 *  output : JSON Format {"Data":[Objects]}
			 */
			@GetMapping(RequestMappingUrls.UNBILLED_URL+RequestMappingUrls.UNBILLEDL3_AGEING_DATA + RequestMappingUrls.CATEGORY_TYPE +
	RequestMappingUrls.AGE)
			public GateWayResponse<?> findWithCatAndAge(@PathVariable String Category_Type , @PathVariable String age){
				try {
				Map<String,List<UnBilledTracker>> ListOfDataWithCatAndPlan = unBilledTrackerService.FindByCatAndAge(Category_Type,age);
				return new GateWayResponse<>(HttpStatus.OK, ListOfDataWithCatAndPlan, MessageKeysUtility.MESSAGE_SUCCESS);
				} catch (NullPointerException nullExc) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
				} catch (BadRequestException badRequestException) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
				} catch (Exception e) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
				}
			}
			
			/*
			 * To retrieve the financial years list. 
			 * URL to use : http://localhost:8080/wpc/utilization/goal/{FY}
			 * URL : http://localhost:8080/wpc/utilization/goal/FY-21
			 * Output : [Objects] 
			 * For now, it returns all the data. Small change in the query gives the data for the given financial year.
			 */
			@GetMapping(RequestMappingUrls.UTILIZATION_URL + RequestMappingUrls.GOAL_URL +RequestMappingUrls.GOAL_YEAR_PARAM)
			public GateWayResponse<?> findFinancialYears(@PathVariable String year){
				try {
				List<FinancialYears> ListOfFinancialYears = utilizationService.findFinancialYears(year);
						return new GateWayResponse<>(HttpStatus.OK, ListOfFinancialYears, MessageKeysUtility.MESSAGE_SUCCESS);
			} catch (NullPointerException nullExc) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
			} catch (BadRequestException badRequestException) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
			} catch (Exception e) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
			}
			
			}
		//Alekhya Ends
		//Varun Begins
			
			@GetMapping(RequestMappingUrls.UTILIZATION_URL+RequestMappingUrls.DUWISE_URL)
			public GateWayResponse<?> printall() throws JSONException {
			try{
				return new GateWayResponse<>(HttpStatus.OK, utilizationService.duWise(), MessageKeysUtility.MESSAGE_SUCCESS);
			} catch (NullPointerException nullExc) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
			} catch (BadRequestException badRequestException) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
			} catch (Exception e) {
				return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
			}
			}
			// task 2 END
			
			// remaining tasks
			
			@GetMapping(RequestMappingUrls.UNBILLED_URL +RequestMappingUrls.UNBILLEDL4_OVERALL_DATA_URL+ RequestMappingUrls.UNBILLEDL4_OVERALL_DATA_URL_DU_PARAM+ RequestMappingUrls.UNBILLEDL4_OVERALL_DATA_URL_PLAN_PARAM)
			public GateWayResponse<?> unbilledL4OverallData(@PathVariable("Du") String Du,@PathVariable("Plan") String plan) {
				List<JSONObject> result=new ArrayList<JSONObject>();
				for(UnBilledTracker x:unBilledTrackerService.FindByPlanAndDu(Du, plan)) {
					result.add(x.unBilledL4PlanDataWithOutMonthAndYear());
				}
				
				try{
					return new GateWayResponse<>(HttpStatus.OK, result, MessageKeysUtility.MESSAGE_SUCCESS);
				} catch (NullPointerException nullExc) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
				} catch (BadRequestException badRequestException) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
				} catch (Exception e) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
				}
			}
			@RequestMapping(RequestMappingUrls.UNBILLED_URL+RequestMappingUrls.UNBILLEDL4_OVERALL_AGE_DATA_URL+RequestMappingUrls.UNBILLEDL4_OVERALL_DATA_URL_DU_PARAM+RequestMappingUrls.UNBILLEDL4_OVERALL_DATA_URL_AGE_PARAM)
			public GateWayResponse<?> unbilledL4AgingData(@PathVariable("Du") String Du,@PathVariable("Age") String Age) {
				List<JSONObject> result=new ArrayList<JSONObject>();
				for(UnBilledTracker x:unBilledTrackerService.FindByDuAndAge(Du, Age)) {
					result.add(x.unBilledL4PlanDataWithOutMonthAndYear());
				}
				
				try{
					return new GateWayResponse<>(HttpStatus.OK, result, MessageKeysUtility.MESSAGE_SUCCESS);
				} catch (NullPointerException nullExc) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
				} catch (BadRequestException badRequestException) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
				} catch (Exception e) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
				}
			}
			@RequestMapping(RequestMappingUrls.UNBILLED_URL+RequestMappingUrls.UNBILLEDL4_OVERALL_PLAN_DATA_URL+RequestMappingUrls.UNBILLEDL4_OVERALL_DATA_URL_DU_PARAM+RequestMappingUrls.UNBILLEDL4_MONTH_PARAM+RequestMappingUrls.UNBILLEDL4_YEAR_PARAM)
			
			public  GateWayResponse<?> unbilledL4PlanData(@PathVariable("Du") String Du,@PathVariable("month") String month,@PathVariable("year") String year) {
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
				try{
					return new GateWayResponse<>(HttpStatus.OK, result, MessageKeysUtility.MESSAGE_SUCCESS);
				} catch (NullPointerException nullExc) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, nullExc.getMessage());
				} catch (BadRequestException badRequestException) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, badRequestException.getMessage());
				} catch (Exception e) {
					return new GateWayResponse<>(HttpStatus.EXPECTATION_FAILED, e.getMessage());
				}
			}
			//Varun Ends
			

}
