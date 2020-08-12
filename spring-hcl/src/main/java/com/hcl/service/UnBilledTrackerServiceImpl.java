package com.hcl.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.entity.UnBilledTracker;
import com.hcl.repository.UnBilledTrackerJdbcRepository;
import com.hcl.repository.UnBilledTrackerRepository;
import com.hcl.util.ConstantUtility;

@Service
public class UnBilledTrackerServiceImpl implements UnBilledTrackerService {
	// Sai Begins
	private UnBilledTrackerRepository unBilledTrackerRepository;
	private UnBilledTrackerJdbcRepository unBilledTrackerJdbcRepository;

	@Autowired
	UnBilledTrackerServiceImpl(UnBilledTrackerRepository unBilledTrackerRepository,UnBilledTrackerJdbcRepository unBilledTrackerJdbcRepository) {
		this.unBilledTrackerRepository = unBilledTrackerRepository;
		this.unBilledTrackerJdbcRepository=unBilledTrackerJdbcRepository;
	}

	@Override
	public Map<String, Map<String, Map<String, Integer>>> getUnBilledDetails() {
		List<UnBilledTracker> list = getLisOfUnbilledDetails();

		Set<String> categoryList = new HashSet();
		list.stream().distinct().forEach(one -> {
			categoryList.add(one.getCategoryType());
		});

		return this.commonCodeForUnbilledTracker(list, categoryList, ConstantUtility.empty);
	}

	private Map<String, Map<String, Map<String, Integer>>> commonCodeForUnbilledTracker(List<UnBilledTracker> list,
			Set<String> categoryList, String DuType) {

		Comparator<UnBilledTracker> comparator = Comparator.comparing(UnBilledTracker::geteBDLWDTRFOutDate);
		UnBilledTracker maxObject = list.stream().max(comparator).get();

		Map<String, Map<String, Integer>> table1Data = new TreeMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> table2Data = new TreeMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> table3Data = new TreeMap<String, Map<String, Integer>>();

		Set<String> plan = new HashSet();
		list.stream().distinct().forEach(one -> {
			plan.add(one.getPlan());
		});

		for (String single : categoryList) {
			List<UnBilledTracker> singleList = GetGroupOfUnbilledDetails(list, single, DuType);
			int lessThen30 = ConstantUtility.zero;
			int between30_60 = ConstantUtility.zero;
			int between60_90 = ConstantUtility.zero;
			int graterThan_90 = ConstantUtility.zero;
			String monthAndYear = ConstantUtility.empty;
			int monthCount = ConstantUtility.zero;
			Map<String, Integer> Typedata = new TreeMap<String, Integer>();
			plan.forEach(one -> {
				Typedata.put(one, 0);

			});
			Map<String, Integer> monthdata = new LinkedHashMap<String, Integer>();
			for (UnBilledTracker unBill : singleList) {
				Typedata.compute(unBill.getPlan(), (k, v) -> v == 0 ? 1 : v + 1);
				if (unBill.getUnbillAgeing() < 30) {
					lessThen30 = lessThen30 + 1;
				} else if (unBill.getUnbillAgeing() > 30 && unBill.getUnbillAgeing() < 60) {
					between30_60 = between30_60 + 1;
				} else if (unBill.getUnbillAgeing() > 60 && unBill.getUnbillAgeing() < 90) {
					between60_90 = between60_90 + 1;
				} else {
					graterThan_90 = graterThan_90 + 1;
				}
				if (monthAndYear.isEmpty()) {
					monthAndYear = ConstantUtility.getMontAndYear(unBill.geteBDLWDTRFOutDate());
					monthCount = 1;

				} else {
					if (monthAndYear.equalsIgnoreCase(ConstantUtility.getMontAndYear(unBill.geteBDLWDTRFOutDate()))) {
						monthCount = monthCount + 1;
					} else {
						monthdata.put(monthAndYear, monthCount);

						monthAndYear = ConstantUtility.getMontAndYear(unBill.geteBDLWDTRFOutDate());
						monthCount = 1;
					}
				}
			}
			monthdata.put(monthAndYear, monthCount);
			Map<String, Integer> agedata = new TreeMap<String, Integer>();
			// Category data
			table1Data.put(single, Typedata);
			agedata.put(ConstantUtility.LESS_THAN_30, lessThen30);
			agedata.put(ConstantUtility.BETWEEN_30_60, between30_60);
			agedata.put(ConstantUtility.BETWEEN_60_90, between60_90);
			agedata.put(ConstantUtility.GRATER_THAN_90, graterThan_90);
			// age data
			table2Data.put(single, agedata);
			// month and year data
			table3Data.put(single, this.adddumeMonnth(monthdata, maxObject.geteBDLWDTRFOutDate()));
		}
		Map<String, Map<String, Map<String, Integer>>> table1FinalData = new TreeMap<String, Map<String, Map<String, Integer>>>();
		table1FinalData.put(ConstantUtility.TABLE1, table1Data);
		table1FinalData.put(ConstantUtility.TABLE2, table2Data);
		table1FinalData.put(ConstantUtility.TABLE3, table3Data);

		return table1FinalData;
	}

	private List<UnBilledTracker> GetGroupOfUnbilledDetails(List<UnBilledTracker> list, String single, String duType) {
		List<UnBilledTracker> singleList = new ArrayList<UnBilledTracker>();
		if (ConstantUtility.DUTYPE.equalsIgnoreCase(duType)) {
			singleList = list.stream().filter(one -> one.getAdjustedDU().equalsIgnoreCase(single))
					.collect(Collectors.toList());

		} else {
			singleList = list.stream().filter(one -> one.getCategoryType().equalsIgnoreCase(single))
					.collect(Collectors.toList());
		}

		singleList.sort((o1, o2) -> o1.geteBDLWDTRFOutDate().compareTo(o2.geteBDLWDTRFOutDate()));
		return singleList;
	}

	private List<UnBilledTracker> getLisOfUnbilledDetails() {
		LocalDate localDate = LocalDate.now().of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 01);
		Date date = Date.valueOf(localDate);
		List<UnBilledTracker> list = unBilledTrackerJdbcRepository.getEBDLWDTRFOutDateGreaterThanEqual(date);
		return list;
	}

	private Map<String, Integer> adddumeMonnth(Map<String, Integer> monthdata, Date MaxDate) {

		LocalDate localMaxDate = MaxDate.toLocalDate().withDayOfMonth(LocalDate.now().getDayOfMonth());
		Map<String, Integer> sortedmonthdata = new LinkedHashMap<String, Integer>();
		for (LocalDate currentDate = LocalDate.now(); currentDate.isBefore(localMaxDate)
				|| currentDate.isEqual(localMaxDate); currentDate = currentDate.plusMonths(1)) {
			String montAndYear = ConstantUtility.getMontAndYear(Date.valueOf(currentDate));
			if (monthdata.containsKey(montAndYear)) {
				sortedmonthdata.put(montAndYear, monthdata.get(montAndYear));
				continue;

			}
			sortedmonthdata.put(montAndYear, 0);

		}
		return sortedmonthdata;

	}

	@Override
	public Map<String, Map<String, Map<String, Integer>>> getUnBilled4Data() {
		List<UnBilledTracker> list = getLisOfUnbilledDetails();
		Set<String> adjustedDUList = new HashSet();
		list.stream().distinct().forEach(one -> {
			adjustedDUList.add(one.getAdjustedDU());
		});
		return this.commonCodeForUnbilledTracker(list, adjustedDUList, ConstantUtility.DUTYPE);

	}

	@Override
	public List<JSONObject> getUnbilledL3PlanData(String type, String month, String year) {

		if (month.equalsIgnoreCase(ConstantUtility.GRAND_TOTAL) && year.equalsIgnoreCase(ConstantUtility.GRAND_TOTAL)) {
			List<UnBilledTracker> list = unBilledTrackerJdbcRepository.findAllByCategoryType(type);
			return this.mapwithMontAndYear(list);

		} else if (!month.equalsIgnoreCase(ConstantUtility.GRAND_TOTAL)
				&& !year.equalsIgnoreCase(ConstantUtility.GRAND_TOTAL)) {
			List<UnBilledTracker> list = unBilledTrackerJdbcRepository.findAllByCategoryTypeAndMonthAndYear(type,
					Integer.parseInt(year), ConstantUtility.getDaysInMonth(month));
			return this.mapwithOutMontAndYear(list);

		} else if (month.equalsIgnoreCase(ConstantUtility.GRAND_TOTAL)
				&& !year.equalsIgnoreCase(ConstantUtility.GRAND_TOTAL)) {
			List<UnBilledTracker> list = unBilledTrackerJdbcRepository.findAllByCategoryTypeAndYear(type,
					Integer.parseInt(year));
			return this.mapwithOutMontAndYear(list);
		}
		return null;
	}

	private List<JSONObject> mapwithMontAndYear(List<UnBilledTracker> list) {
		List<JSONObject> finalList = new ArrayList<>();
		for (UnBilledTracker single : list) {
			finalList.add(single.unBilledL4PlanDataWithMonthAndYear());
		}

		return finalList;

	}

	private List<JSONObject> mapwithOutMontAndYear(List<UnBilledTracker> list) {
		List<JSONObject> finalList = new ArrayList<>();
		for (UnBilledTracker single : list) {
			finalList.add(single.unBilledL4PlanDataWithOutMonthAndYear());
		}
		return finalList;

	}

	// Sai Ends

	// Alekhya Begin
	@Override
	public Map<String, List<UnBilledTracker>> findWithCatAndPlan(String categoryType, String plan) {
		Map<String,List<UnBilledTracker>> JsonResult = new HashMap<>(); 
		JsonResult.put("Data", unBilledTrackerJdbcRepository.findWithCatAndPlan(categoryType,plan));
		return JsonResult;
	}

	@Override
	public Map<String, List<UnBilledTracker>> FindByCatAndAge(String categoryType, String age) {
		Map<String,List<UnBilledTracker>> JsonResult = new HashMap<>(); 
		JsonResult.put("Data", unBilledTrackerJdbcRepository.findWithCatAndAge(categoryType,age));
		return JsonResult;
	}

	// Alekhya Ends

	// Varun Begins

	public List<UnBilledTracker> FindByPlanAndDu(String Du, String Plan) {
		if (Plan.contains("Grand Total")) {
			return unBilledTrackerRepository.findAllByAdjustedDU(Du);
		} else {
			return unBilledTrackerRepository.findAllByAdjustedDUAndPlan(Du, Plan);
		}
	}

	public List<UnBilledTracker> FindByDuAndAge(String Du, String Age) {
		if (Age.contains("< 30 days")) {
			return unBilledTrackerRepository.findAllByAdjustedDUAndUnbillAgeingLessThanEqual(Du, 30);
		} else if (Age.contains("30-60 days")) {
			return unBilledTrackerRepository
					.findAllByAdjustedDUAndUnbillAgeingGreaterThanAndUnbillAgeingLessThanEqual(Du, 30, 60);
		} else if (Age.contains("60-90-Days")) {
			return unBilledTrackerRepository
					.findAllByAdjustedDUAndUnbillAgeingGreaterThanAndUnbillAgeingLessThanEqual(Du, 60, 90);
		} else if (Age.contains("> 90-Days")) {
			return unBilledTrackerRepository.findAllByadjustedDUAndUnbillAgeingGreaterThan(Du, 90);
		} else {
			return unBilledTrackerRepository.findAllByAdjustedDU(Du);
		}
	}

	public enum monthNames {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	};

	public List<UnBilledTracker> FindByDuAndYearAndMonth(String Du, String month, String year) {

		if (month.contains("Grand Total") && year.contains("Grand Total")) {
			return unBilledTrackerRepository.findAllByAdjustedDU(Du);
		} else if (month.contains("Grand Total") && !year.contains("Grand Total")) {
			return unBilledTrackerRepository.findAllByDuAndYear(Du, Integer.parseInt(year));
		} else {
			int monthint = monthNames.valueOf(month).ordinal();
			return unBilledTrackerRepository.findAllByDuAndMonthAndYear(Du, Integer.parseInt(year), monthint + 1);
		}
	}

	// Varun Ends

}
