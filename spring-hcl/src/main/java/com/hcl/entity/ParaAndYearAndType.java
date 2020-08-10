package com.hcl.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

public class ParaAndYearAndType implements Serializable {
	String parameter;
	String year;
	String type;

	public ParaAndYearAndType() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		ParaAndYearAndType other = (ParaAndYearAndType) obj;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	public ParaAndYearAndType(String parameter, String year, String type) {
		super();
		this.parameter = parameter;
		this.year = year;
		this.type = type;
	}

	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "ParaAndYearAndType [Parameter=" + parameter + ", Year=" + year + ", Type=" + type + "]";
	}
}
