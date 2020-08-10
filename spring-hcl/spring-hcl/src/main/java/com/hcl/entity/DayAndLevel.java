package com.hcl.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Embeddable;

public class DayAndLevel implements Serializable{
	   Date Calendar_Day;
	   String Level;
	   
	public DayAndLevel() {
		super();
	}

	public DayAndLevel(Date calendar_Day, String level) {
		super();
		Calendar_Day = calendar_Day;
		Level = level;
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

	@Override
	public String toString() {
		return "DayAndLevel [Calendar_Day=" + Calendar_Day + ", Level=" + Level + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Calendar_Day == null) ? 0 : Calendar_Day.hashCode());
		result = prime * result + ((Level == null) ? 0 : Level.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayAndLevel other = (DayAndLevel) obj;
		if (Calendar_Day == null) {
			if (other.Calendar_Day != null)
				return false;
		} else if (!Calendar_Day.equals(other.Calendar_Day))
			return false;
		if (Level == null) {
			if (other.Level != null)
				return false;
		} else if (!Level.equals(other.Level))
			return false;
		return true;
	}
	
}
