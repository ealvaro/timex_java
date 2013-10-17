package com.visualpatterns.timex.model;

import java.io.Serializable;

/**
 * @author Alvaro E. Escobar
 */
public class ManagerSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private Employee manager;
	private int totalMinutes = 0;
	private int approved = 0;
	private int disapproved = 0;
	private int unpaid = 0;
	private int paid = 0;

	public ManagerSummary(Employee manager) {
		this.manager = manager;
	}

	/**
	 * Add minutes and increment counter for given status code.
	 * 
	 * @param minutes
	 * @param timesheetStatusCode
	 */
	public void addMinutes(int minutes, String timesheetStatusCode) {
		if (timesheetStatusCode.equalsIgnoreCase(Timesheet.SUBMITTED)) {
			++unpaid;
		} else if (timesheetStatusCode.equalsIgnoreCase(Timesheet.PENDING)) {
			++unpaid;
		} else if (timesheetStatusCode.equalsIgnoreCase(Timesheet.APPROVED)) {
			++approved;
			++unpaid;
		} else if (timesheetStatusCode.equalsIgnoreCase(Timesheet.DISAPPROVED)) {
			++disapproved;
			++unpaid;
		} else if (timesheetStatusCode.equalsIgnoreCase(Timesheet.PAID)) {
			++paid;
		} else {
			throw new IllegalArgumentException("Missing timesheet status code");
		}
		totalMinutes += minutes;
	}

	public Employee getManager() {
		return manager;
	}

	public int getTotalMinutes() {
		return totalMinutes;
	}

	public int getPaid() {
		return paid;
	}

	public int getUnpaid() {
		return unpaid;
	}

	public int getApproved() {
		return approved;
	}

	public int getDisapproved() {
		return disapproved;
	}

}
