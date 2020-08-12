package com.hcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Goals;
import com.hcl.entity.ParaAndYearAndType;
import com.hcl.util.ConstantUtility;

@Repository
public interface GoalJpaRepository extends JpaRepository<Goals, ParaAndYearAndType> {

	
	
	@Query(value="select max(YEAR) from tbl_wpc_Goals",  nativeQuery = true)
	public String FindMaxDate();
	
	@Query(value = "{call usp_wpc_GetGoals(:Year)}",nativeQuery = true)
	public List<Goals> usp_wpc_GetGoals(@Param("Year") String Year);
}
