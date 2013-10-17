package com.visualpatterns.timex.model;

import java.io.Serializable;

/**
 * @author Anil Hemrajani
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	String departmentCode;
	String name;

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
