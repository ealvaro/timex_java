package com.visualpatterns.timex.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class Timesheet implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SUBMITTED = "S";
	public static final String PENDING = "P";
	public static final String APPROVED = "A";
	public static final String DISAPPROVED = "D";
	public static final String PAID = "C";

	private int timesheetId;
	private int employeeId;
	private int minutesMon = 0;
	private int minutesTue = 0;
	private int minutesWed = 0;
	private int minutesThu = 0;
	private int minutesFri = 0;
	private int minutesSat = 0;
	private int minutesSun = 0;

	private String statusCode;
	private String departmentCode;
	private Date periodEndingDate;
	private Department department;
	private Employee employee;

	/**
	 * @return total minutes for the week
	 */
	public int getTotalMinutes() {
		return (minutesMon + minutesTue + minutesWed + minutesThu + minutesFri
				+ minutesSat + minutesSun);
	}

	public boolean isApproved() {
		return statusCode.equalsIgnoreCase(APPROVED);
	}

	public boolean isPending() {
		return this.statusCode.equalsIgnoreCase(PENDING)
				|| statusCode.equalsIgnoreCase(DISAPPROVED);
	}

	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Possible values:
	 * <ul>
	 * <li />PENDING: "P"
	 * <li />APPROVED: "A"
	 * <li />PAID: "C"
	 * <li />SUBMITTED: "S"
	 * <li />DISAPPROVED: "D"
	 * </ul>
	 * 
	 * @param statusCode
	 */
	public void setStatusCode(String statusCode) {
		if (statusCode.length() > 1)
			throw new IllegalArgumentException("Status code length must be 1");
		this.statusCode = statusCode;
	}

	public int getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(int timesheetId) {
		this.timesheetId = timesheetId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getMinutesMon() {
		return minutesMon;
	}

	public void setMinutesMon(int minutesMon) {
		this.minutesMon = minutesMon;
	}

	public int getMinutesTue() {
		return minutesTue;
	}

	public void setMinutesTue(int minutesTue) {
		this.minutesTue = minutesTue;
	}

	public int getMinutesWed() {
		return minutesWed;
	}

	public void setMinutesWed(int minutesWed) {
		this.minutesWed = minutesWed;
	}

	public int getMinutesThu() {
		return minutesThu;
	}

	public void setMinutesThu(int minutesThu) {
		this.minutesThu = minutesThu;
	}

	public int getMinutesFri() {
		return minutesFri;
	}

	public void setMinutesFri(int minutesFri) {
		this.minutesFri = minutesFri;
	}

	public int getMinutesSat() {
		return minutesSat;
	}

	public void setMinutesSat(int minutesSat) {
		this.minutesSat = minutesSat;
	}

	public int getMinutesSun() {
		return minutesSun;
	}

	public void setMinutesSun(int minutesSun) {
		this.minutesSun = minutesSun;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Date getPeriodEndingDate() {
		return periodEndingDate;
	}

	public void setPeriodEndingDate(Date periodEndingDate) {
		this.periodEndingDate = periodEndingDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
