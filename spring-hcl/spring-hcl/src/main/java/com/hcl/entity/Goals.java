package com.hcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_wpc_Goals")
@IdClass(ParaAndYearAndType.class)
public class Goals implements Cloneable{

	@Id
	@Column(name = "Parameter")
	private String parameter;
	@Column(name = "EffectiveUtil")
	private Float effectiveUtil;
	@Column(name = "UnbilledInProjects")
	private Float unbilledInProjects;
	@Column(name = "InternalProjects")
	private Float internalProjects;
	@Column(name = "Bench")
	private Float bench;
	@Column(name = "DeliverySupport")
	private Float deliverySupport;
	@Id
	@Column(name = "Year")
	private String year;
	@Id
	@Column(name = "Type")
	private String type;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Float getEffectiveUtil() {
		return effectiveUtil;
	}

	public void setEffectiveUtil(Float effectiveUtil) {
		this.effectiveUtil = effectiveUtil;
	}

	public Float getUnbilledInProjects() {
		return unbilledInProjects;
	}

	public void setUnbilledInProjects(Float unbilledInProjects) {
		this.unbilledInProjects = unbilledInProjects;
	}

	public Float getInternalProjects() {
		return internalProjects;
	}

	public void setInternalProjects(Float internalProjects) {
		this.internalProjects = internalProjects;
	}

	public Float getBench() {
		return bench;
	}

	public void setBench(Float bench) {
		this.bench = bench;
	}

	public Float getDeliverySupport() {
		return deliverySupport;
	}

	public void setDeliverySupport(Float deliverySupport) {
		this.deliverySupport = deliverySupport;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Goals(String parameter, Float effectiveUtil, Float unbilledInProjects, Float internalProjects, Float bench,
			Float deliverySupport, String year, String type) {
		super();
		this.parameter = parameter;
		this.effectiveUtil = effectiveUtil;
		this.unbilledInProjects = unbilledInProjects;
		this.internalProjects = internalProjects;
		this.bench = bench;
		this.deliverySupport = deliverySupport;
		this.year = year;
		this.type = type;
	}

	public Goals() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 public Object clone() throws
     CloneNotSupportedException 
{ 
return super.clone(); 
} 

}
