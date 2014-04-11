package com.timexautoweb.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.ManagerSummary;
import com.timexautoweb.domain.ManagerSummaryList;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.DateUtil;

/**
 * @author Alvaro E. Escobar
 */
public class ManagerSummaryController extends SimpleFormController {

	private EmployeeHome employeeManager;
	private TimesheetHome timesheetManager;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List<ManagerSummary> summaries = new ArrayList<ManagerSummary>();
		List<Employee> managers = employeeManager.getManagers();
		Date periodEndingDate = DateUtil.getCurrentPeriodEndingDate();

		ManagerSummary managerSummary;
		List<Employee> reportingEmployees;
		Timesheet timesheet;
		for (Employee manager : managers) {
			managerSummary = new ManagerSummary(manager);
			reportingEmployees = employeeManager.getReportingEmployees(manager.getId());
			for (Employee employee : reportingEmployees) {
				timesheet = timesheetManager.getTimesheet(employee.getId(), periodEndingDate);
				if (timesheet != null) {
					managerSummary.addMinutes(timesheet.getTotalMinutes(), timesheet.getStatusCode());
				}
			}
			summaries.add(managerSummary);
		}
		return new ManagerSummaryList(summaries);
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

}
