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

	@Query(value = ConstantUtility.GET_GOAL_PROCEDURE_NAME, nativeQuery = true)
	public List<Goals> getAllgolsByYear(@Param(ConstantUtility.GET_GOAL_PROCEDURE_PARAMETER) String year);

	@Modifying
	@Query(value = ConstantUtility.EDIT_GOAL_PROCEDURE_NAME, nativeQuery = true)
	public void updateOrSaveTheGoal(@Param(ConstantUtility.PARAMETER_TYPE) String parameter,
			@Param(ConstantUtility.EFFECTIVEUTIL_TYPE) Float effectiveUtilType,
			@Param(ConstantUtility.UNBILLED_IN_PROJECTS_TYPE) Float unbilledInProject,
			@Param(ConstantUtility.INTERNAL_PROJECTS_TYPE) Float internalProjectType,
			@Param(ConstantUtility.BENCH_TYPE) Float benchType,
			@Param(ConstantUtility.DELIVERY_SUPPORT_TYPE) Float deliverySupportType,
			@Param(ConstantUtility.YEAR_TYPE) String year, @Param(ConstantUtility.TYPE_TYPE) String type);

	@Query(value = ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_NAME, nativeQuery = true)
	public List<Goals> getSpecificGoal(@Param(ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_PARAMETER) String parameter);

	@Query(value = ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_NAME, nativeQuery = true)
	public List<Goals> getSpecificduGoal(@Param(ConstantUtility.GET_SPICIFIC_GOAL_PROCEDURE_PARAMETER) String level);
	
	////////////////////////////////////////////////////////////////
	
	@Query(value="select max(YEAR) from tbl_wpc_Goals",  nativeQuery = true)
	public String FindMaxDate();
	
	@Query(value = "{call usp_wpc_GetGoals(:Year)}",nativeQuery = true)
	public List<Goals> usp_wpc_GetGoals(@Param("Year") String Year);
}
