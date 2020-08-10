package com.hcl.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.entity.DayAndLevel;
import com.hcl.entity.ProjectBillSplit;


@Repository
public interface Repo_Project_bill_split extends JpaRepository<ProjectBillSplit,DayAndLevel> {
	@Query(value="select Calendar_Day,Level,SSB,SUT,IFD_Freshers,C_unbilled_Freshers,Billed_Freshers from Project_Bill_Split where Calendar_Day>=? and Calendar_Day<=?",nativeQuery=true)
	public List<ProjectBillSplit> FindRecords(Date startDate,Date endDate);
}
