package com.visualpatterns.timex.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.visualpatterns.timex.model.Employee;
import com.visualpatterns.timex.model.EmployeeManager;
import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetList;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.ApplicationSecurityManager;

/**
 * @author Alvaro E. Escobar
 */
public class StaffHoursController extends SimpleFormController {

	private TimesheetManager timesheetManager;
	private EmployeeManager employeeManager;
	private ApplicationSecurityManager applicationSecurityManager;

	/**
	 * Returns a list of non-paid Timesheets from employees that report to
	 * signed-in employee.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Employee employee = (Employee) applicationSecurityManager
				.getEmployee(request);
		List<Employee> reportingEmployees = employeeManager
				.getReportingEmployees(employee.getEmployeeId());
		List<Timesheet> timesheets = new ArrayList<Timesheet>();
		for (Employee e : reportingEmployees) {
			Integer employeeId = e.getEmployeeId();
			List<Timesheet> empTimesheets = timesheetManager
					.getTimesheets(employeeId);
			for (Timesheet t : empTimesheets) {
				if (t.getStatusCode().equalsIgnoreCase(Timesheet.PAID) == false) {
					timesheets.add(t);
				}
			}
		}
		return new TimesheetList(timesheets);
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

}
