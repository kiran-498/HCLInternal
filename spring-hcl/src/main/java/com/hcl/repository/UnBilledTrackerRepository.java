package com.hcl.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcl.entity.UnBilledTracker;
import com.hcl.util.ConstantUtility;

public interface UnBilledTrackerRepository extends JpaRepository<UnBilledTracker, String> {

/*	@Query(value = ConstantUtility.SELECT_UN_BILLED, nativeQuery = true)
	List<UnBilledTracker> getEBDLWDTRFOutDateGreaterThanEqual(Date date);*/

	//public List<UnBilledTracker> findAllByCategoryType(String categoryType);

	//@Query(value = "select * from UnBilled_Tracker where Category_Type = ? and DATEPART(YYYY,EBD_LWD_TRF_Out_Date)=? and DATEPART(MONTH,EBD_LWD_TRF_Out_Date)=? ", nativeQuery = true)
	//public List<UnBilledTracker> findAllByCategoryTypeAndMonthAndYear(String categoryType, int year, int month);

	//@Query(value = "select * from UnBilled_Tracker where Category_Type = ? and DATEPART(YYYY,EBD_LWD_TRF_Out_Date)=?", nativeQuery = true)
	//public List<UnBilledTracker> findAllByCategoryTypeAndYear(String categoryType, int year);
	
	/////////////////////////////////////////////////////////////////
	
	public List<UnBilledTracker> findAllByAdjustedDUAndPlan(String Du, String Plan);
	public List<UnBilledTracker> findAllByAdjustedDU(String Du);
	public List<UnBilledTracker> findAllByAdjustedDUAndUnbillAgeingLessThanEqual(String Du, int Age);
	public List<UnBilledTracker> findAllByadjustedDUAndUnbillAgeingGreaterThan(String Du, int Age);
	public List<UnBilledTracker> findAllByAdjustedDUAndUnbillAgeingGreaterThanAndUnbillAgeingLessThanEqual(String du, int i, int j);
	@Query(value="select * from UnBilled_Tracker where Adjusted_Du = ? and DATEPART(YYYY,EBD_LWD_TRF_Out_Date)=?",nativeQuery=true)
	public List<UnBilledTracker> findAllByDuAndYear(String du, int year);
	@Query(value="select * from UnBilled_Tracker where Adjusted_Du = ? and DATEPART(YYYY,EBD_LWD_TRF_Out_Date)=? and DATEPART(MONTH,EBD_LWD_TRF_Out_Date)=? ",nativeQuery=true)
	public List<UnBilledTracker> findAllByDuAndMonthAndYear(String du, int year, int month);
	
	//Alekhya Begins
	
	public List<UnBilledTracker> findAllByCategoryTypeAndPlan(String categoryType, String plan);
//	@Query(value="select * from UnBilled_Tracker where Adjusted_Du = ? and Unbill_Ageing<=?",nativeQuery=true)
	
	public List<UnBilledTracker> findAllByCategoryTypeAndUnbillAgeingLessThanEqual(String categoryType, int Age); 
	public List<UnBilledTracker> findAllByCategoryTypeAndUnbillAgeingGreaterThan(String Du, int Age);
	
//	@Query(value ="select * from UnBilled_Tracker where Category_Type =? AND Unbill_Ageing > ? and Unbill_Ageing <= ?",nativeQuery=true)
	public List<UnBilledTracker> findAllByCategoryTypeAndUnbillAgeingGreaterThanAndUnbillAgeingLessThanEqual(String du, int i, int j);
	
	//Alekhya Ends

}
