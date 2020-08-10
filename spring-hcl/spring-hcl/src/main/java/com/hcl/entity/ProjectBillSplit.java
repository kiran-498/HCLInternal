package com.hcl.entity;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(DayAndLevel.class)
public class ProjectBillSplit {
	@Id
	Date Calendar_Day;
	@Id   
	String Level;
   Float SSB;
   Float SUT;
   Float IFD_Freshers;
   Float C_unbilled_Freshers;
   Float Billed_Freshers;
   
   public ProjectBillSplit(Date calendar_Day, String level, Float sSB, Float sUT, Float iFD_Freshers,
			Float c_unbilled_Freshers, Float billed_Freshers) {
		super();
		Calendar_Day = calendar_Day;
		Level = level;
		SSB = sSB;
		SUT = sUT;
		IFD_Freshers = iFD_Freshers;
		C_unbilled_Freshers = c_unbilled_Freshers;
		Billed_Freshers = billed_Freshers;
	}
	/**
	 * @return the calendar_Day
	 */
	public Date getCalendar_Day() {
		return Calendar_Day;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return Level;
	}
	/**
	 * @param calendar_Day the calendar_Day to set
	 */
	public void setCalendar_Day(Date calendar_Day) {
		Calendar_Day = calendar_Day;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		Level = level;
	}
	/**
	 * @return the sSB
	 */
	public Float getSSB() {
		return SSB;
	}
	/**
	 * @param sSB the sSB to set
	 */
	public void setSSB(Float sSB) {
		SSB = sSB;
	}
	/**
	 * @return the sUT
	 */
	public Float getSUT() {
		return SUT;
	}
	/**
	 * @param sUT the sUT to set
	 */
	public void setSUT(Float sUT) {
		SUT = sUT;
	}
	/**
	 * @return the iFD_Freshers
	 */
	public Float getIFD_Freshers() {
		return IFD_Freshers;
	}
	/**
	 * @param iFD_Freshers the iFD_Freshers to set
	 */
	public void setIFD_Freshers(Float iFD_Freshers) {
		IFD_Freshers = iFD_Freshers;
	}
	/**
	 * @return the c_unbilled_Freshers
	 */
	public Float getC_unbilled_Freshers() {
		return C_unbilled_Freshers;
	}
	/**
	 * @param c_unbilled_Freshers the c_unbilled_Freshers to set
	 */
	public void setC_unbilled_Freshers(Float c_unbilled_Freshers) {
		C_unbilled_Freshers = c_unbilled_Freshers;
	}
	/**
	 * @return the billed_Freshers
	 */
	public Float getBilled_Freshers() {
		return Billed_Freshers;
	}
	/**
	 * @param billed_Freshers the billed_Freshers to set
	 */
	public void setBilled_Freshers(Float billed_Freshers) {
		Billed_Freshers = billed_Freshers;
	}
	@Override
	public String toString() {
		return "Project_Bill_Split [Calendar_Day=" + Calendar_Day + ", Level=" + Level + ", SSB=" + SSB + ", SUT=" + SUT
				+ ", IFD_Freshers=" + IFD_Freshers + ", C_unbilled_Freshers=" + C_unbilled_Freshers + ", Billed_Freshers="
				+ Billed_Freshers + "]";
	}
	public ProjectBillSplit() {
		super();
	}


}
