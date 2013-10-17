package com.visualpatterns.timex.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.visualpatterns.timex.model.Employee;
import com.visualpatterns.timex.model.EmployeeManager;
import com.visualpatterns.timex.model.ManagerSummary;
import com.visualpatterns.timex.model.ManagerSummaryList;
import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.DateUtil;

/**
 * @author Alvaro E. Escobar
 */
public class ManagerSummaryController extends SimpleFormController {

	private EmployeeManager employeeManager;
	private TimesheetManager timesheetManager;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(
	 *      javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		List<ManagerSummary> summaries = new ArrayList<ManagerSummary>();
		List<Employee> managers = employeeManager.getManagers();
		Date periodEndingDate = DateUtil.getCurrentPeriodEndingDate();

		ManagerSummary managerSummary;
		List<Employee> reportingEmployees;
		Timesheet timesheet;
		for (Employee manager : managers) {
			managerSummary = new ManagerSummary(manager);
			reportingEmployees = employeeManager.getReportingEmployees(manager
					.getEmployeeId());
			for (Employee employee : reportingEmployees) {
				timesheet = timesheetManager.getTimesheet(employee
						.getEmployeeId(), periodEndingDate);
				if (timesheet != null) {
					managerSummary.addMinutes(timesheet.getTotalMinutes(),
							timesheet.getStatusCode());
				}
			}
			summaries.add(managerSummary);
		}
		return new ManagerSummaryList(summaries);
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

}
