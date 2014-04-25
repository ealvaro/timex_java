package com.timexautoweb.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.domain.TimesheetList;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.TimexJmxBean;

/**
 * @author Alvaro E. Escobar
 */
public class StaffHoursController extends SimpleFormController {

	private TimesheetHome timesheetManager;
	private EmployeeHome employeeManager;
	private ApplicationSecurityManager applicationSecurityManager;
	private TimexJmxBean timexJmxBean;

	/**
	 * Returns a list of non-paid Timesheets from employees that report to
	 * signed-in employee.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Employee employee = (Employee) applicationSecurityManager.getEmployee(request);
		List<Employee> reportingEmployees = employeeManager.getReportingEmployees(employee.getId());
		List<Timesheet> timesheets = new ArrayList<Timesheet>();
		for (Employee e : reportingEmployees) {
			Integer employeeId = e.getId();
			List<Timesheet> empTimesheets = timesheetManager.getTimesheets(employeeId);
			timexJmxBean.setTimesheetsFetched(timexJmxBean.getTimesheetsFetched() + empTimesheets.size());
			for (Timesheet t : empTimesheets) {
				if (t.getStatusCode() != Timesheet.PAID) {
					timesheets.add(t);
				}
			}
		}
		return new TimesheetList(timesheets);
	}

	/**
	 * Returns Employee that is login.  Will be used by JSP
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		HashMap model = new HashMap();
		model.put("employee", (Employee) applicationSecurityManager.getEmployee(request));
		return model;
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public TimexJmxBean getTimexJmxBean() {
		return timexJmxBean;
	}

	public void setTimexJmxBean(TimexJmxBean timexJmxBean) {
		this.timexJmxBean = timexJmxBean;
	}

}
