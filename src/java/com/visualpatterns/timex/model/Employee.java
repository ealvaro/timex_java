package com.visualpatterns.timex.model;

import java.io.Serializable;

/**
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String HOURLY = "H";
	public static final String MANAGER = "M";
	public static final String EXECUTIVE = "E";
	public static final String ACCOUNTING = "A";

	private int employeeId;
	private int managerEmployeeId;
	private String name;
	private String password;
	private String email;
	private String employeeCode;

	/**
	 * @return Employee type: Staff, Management, Executive, or Accounting
	 */
	public String getType() {
		if (employeeCode.equalsIgnoreCase("H")) {
			return "Staff";
		} else if (employeeCode.equalsIgnoreCase("M")) {
			return "Management";
		} else if (employeeCode.equalsIgnoreCase("E")) {
			return "Executive";
		} else if (employeeCode.equalsIgnoreCase("A")) {
			return "Accounting";
		} else {
			return "";
		}
	}

	/**
	 * The possible options are:
	 * <ul>
	 * <li />'H' for Hourly (regular employee)
	 * <li />'M' for Manager
	 * <li />'E' for Executive
	 * <li />'A' for Accounting
	 * </ul>
	 * 
	 * @return employeeCode
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getManagerEmployeeId() {
		return managerEmployeeId;
	}

	public void setManagerEmployeeId(int managerEmployeeId) {
		this.managerEmployeeId = managerEmployeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
