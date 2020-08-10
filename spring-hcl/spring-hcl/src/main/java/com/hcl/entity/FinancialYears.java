package com.hcl.entity;

import javax.persistence.Id;

public class FinancialYears {
	@Id
	private  Integer id;
	
	private String years;
	private String fy;
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getFy() {
		return fy;
	}
	public void setFy(String fy) {
		this.fy = fy;
	}
	
	

}
