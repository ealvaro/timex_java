package com.visualpatterns.timex.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alvaro E. Escobar
 */
public class TimesheetList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Timesheet> timesheets = null;

	public TimesheetList(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public List<Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public int getSize() {
		return timesheets.size();
	}

}
