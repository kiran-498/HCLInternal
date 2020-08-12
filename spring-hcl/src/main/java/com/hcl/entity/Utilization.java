package com.hcl.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
@Entity
@IdClass(DayAndLevel.class)
public class Utilization implements Cloneable{
	@Id
	@Column(name ="Calendar_Day")
	   Date Calendar_Day;
    @Id
    @Column(name ="Level")
	String Level;
       Double Effective_Utilization;
       Double Unbilled_In_Projects;
       Double Internal_Projects_SUT;
       Double Bench;
       Double Delivery_Support;
       public Object clone() throws
       CloneNotSupportedException 
  { 
  return super.clone(); 
  } 
		/**
		 * @return the calendar_Day
		 */
		public Date getCalendar_Day() {
			return Calendar_Day;
		}

		/**
		 * @param calendar_Day the calendar_Day to set
		 */
		public void setCalendar_Day(Date calendar_Day) {
			Calendar_Day = calendar_Day;
		}

		/**
		 * @return the level
		 */
		public String getLevel() {
			return Level;
		}

		/**
		 * @param level the level to set
		 */
		public void setLevel(String level) {
			Level = level;
		}

		/**
		 * @return the effective_Utilization
		 */
		public Double getEffective_Utilization() {
			return Effective_Utilization;
		}

		/**
		 * @param effective_Utilization the effective_Utilization to set
		 */
		public void setEffective_Utilization(Double effective_Utilization) {
			Effective_Utilization = effective_Utilization;
		}

		/**
		 * @return the unbilled_In_Projects
		 */
		public Double getUnbilled_In_Projects() {
			return Unbilled_In_Projects;
		}

		/**
		 * @param unbilled_In_Projects the unbilled_In_Projects to set
		 */
		public void setUnbilled_In_Projects(Double unbilled_In_Projects) {
			Unbilled_In_Projects = unbilled_In_Projects;
		}

		/**
		 * @return the internal_Projects_SUT
		 */
		public Double getInternal_Projects_SUT() {
			return Internal_Projects_SUT;
		}

		/**
		 * @param internal_Projects_SUT the internal_Projects_SUT to set
		 */
		public void setInternal_Projects_SUT(Double internal_Projects_SUT) {
			Internal_Projects_SUT = internal_Projects_SUT;
		}

		/**
		 * @return the bench
		 */
		public Double getBench() {
			return Bench;
		}

		/**
		 * @param bench the bench to set
		 */
		public void setBench(Double bench) {
			Bench = bench;
		}

		/**
		 * @return the delivery_Support
		 */
		public Double getDelivery_Support() {
			return Delivery_Support;
		}

		/**
		 * @param delivery_Support the delivery_Support to set
		 */
		public void setDelivery_Support(Double delivery_Support) {
			Delivery_Support = delivery_Support;
		}

		@Override
		public String toString() {
			return "Utilization [Calendar_Day=" + Calendar_Day + ", Level=" + Level + ", Effective_Utilization="
					+ Effective_Utilization + ", Unbilled_In_Projects=" + Unbilled_In_Projects
					+ ", Internal_Projects_SUT=" + Internal_Projects_SUT + ", Bench=" + Bench + ", Delivery_Support="
					+ Delivery_Support + "]";
		}

		public Utilization(Date calendar_Day, String level, Double effective_Utilization, Double unbilled_In_Projects,
				Double internal_Projects_SUT, Double bench, Double delivery_Support) {
			super();
			Calendar_Day = calendar_Day;
			Level = level;
			Effective_Utilization = effective_Utilization;
			Unbilled_In_Projects = unbilled_In_Projects;
			Internal_Projects_SUT = internal_Projects_SUT;
			Bench = bench;
			Delivery_Support = delivery_Support;
		}

		public Utilization() {
			super();
		}
		
        
}
