package com.hcl.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.entity.DayAndLevel;
import com.hcl.entity.FinancialYears;
import com.hcl.entity.Utilization;




@Repository
public interface Repo_Utilization extends JpaRepository<Utilization,DayAndLevel>{
	//Varun
	@Query(" select max(Calendar_Day) from Utilization")
	public Date FindMaxDate();
	@Query(value="select distinct Level from Utilization where Calendar_Day = ? order by Level Asc",nativeQuery=true)
	public List<String> FindDUListOrderByLevelAsc(Date date);
	@Query(value="select Calendar_Day,Level,Effective_Utilization,Unbilled_In_Projects,Internal_Projects_SUT,Bench,Delivery_Support from Utilization where Calendar_Day >= ? and Calendar_Day <= ? ",nativeQuery=true)
	public List<Utilization> FindRecords(Date startDate,Date endDate);
	//Alekhya
	@Query(value = "SELECT * FROM dbo.Utilization WHERE Calendar_Day = ?",nativeQuery=true)
	public List<Utilization> findAll(Date MaxDate);
	//@Query("select * from currentFY")
	//public List<FinancialYears> findFinancialYears();	
}
