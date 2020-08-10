package com.hcl.pojo;

import com.hcl.entity.Goals;
import com.hcl.entity.ProjectBillSplit;
import com.hcl.entity.Utilization;

public class ResultBean implements Cloneable {
	private Utilization actual_Count;
	private Goals goal_Count;
	private ProjectBillSplit project_Bill_Split;
	 public Object clone() throws
     CloneNotSupportedException 
{ 
return super.clone(); 
} 
	public ResultBean(Utilization actual_Count, Goals goal_Count, ProjectBillSplit project_Bill_Split) {
		super();
		this.actual_Count = actual_Count;
		this.goal_Count = goal_Count;
		this.project_Bill_Split = project_Bill_Split;
	}
	public ResultBean() {
		super();
	}
	public ResultBean(Utilization actual_Count, ProjectBillSplit project_Bill_Split) {
		super();
		this.actual_Count = actual_Count;
		this.project_Bill_Split = project_Bill_Split;
	}
	public ResultBean(Goals goal_Count, ProjectBillSplit project_Bill_Split) {
		super();
		this.goal_Count = goal_Count;
		this.project_Bill_Split = project_Bill_Split;
	}
	public ResultBean(Utilization actual_Count, Goals goal_Count) {
		super();
		this.actual_Count = actual_Count;
		this.goal_Count = goal_Count;
	}
	public ResultBean(ProjectBillSplit project_Bill_Split) {
		super();
		this.project_Bill_Split = project_Bill_Split;
	}
	public ResultBean(Goals goal_Count) {
		super();
		this.goal_Count = goal_Count;
	}
	public ResultBean(Utilization actual_Count) {
		super();
		this.actual_Count = actual_Count;
	}
	/**
	 * @return the actual_Count
	 */
	public Utilization getActual_Count() {
		return actual_Count;
	}
	
	/**
	 * @return the project_Bill_Split
	 */
	public ProjectBillSplit getProject_Bill_Split() {
		return project_Bill_Split;
	}
	/**
	 * @param actual_Count the actual_Count to set
	 */
	public void setActual_Count(Utilization actual_Count) {
		this.actual_Count = actual_Count;
	}


	public Goals getGoal_Count() {
		return goal_Count;
	}
	public void setGoal_Count(Goals goal_Count) {
		this.goal_Count = goal_Count;
	}
	/**
	 * @param project_Bill_Split the project_Bill_Split to set
	 */
	public void setProject_Bill_Split(ProjectBillSplit project_Bill_Split) {
		this.project_Bill_Split = project_Bill_Split;
	}
	@Override
	public String toString() {
		return "ResultBean [actual_Count=" + actual_Count + ", goal_Count=" + goal_Count + ", project_Bill_Split="
				+ project_Bill_Split + "]";
	}

}
