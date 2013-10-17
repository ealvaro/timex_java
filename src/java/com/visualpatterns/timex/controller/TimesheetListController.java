package com.visualpatterns.timex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.visualpatterns.timex.model.Employee;
import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.ApplicationSecurityManager;
import com.visualpatterns.timex.util.TimexJmxBean;

/**
 * Controller for the Timesheet List screen.
 * 
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class TimesheetListController implements Controller {

	public static final String MAP_KEY = "timesheets";

	public static final String EMP_KEY = "employee";

	private TimesheetManager timesheetManager;

	private ApplicationSecurityManager applicationSecurityManager;

	private String successView;

	private TimexJmxBean timexJmxBean;

	/**
	 * Returns a list of Timesheet database objects in ModelAndView.
	 * 
	 * @see com.visualpatterns.timex.model.Timesheet
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee employee = (Employee) applicationSecurityManager.getEmployee(request);
		List<Timesheet> timesheets = timesheetManager.getTimesheets(employee.getEmployeeId());
		timexJmxBean.setTimesheetsFetched(timexJmxBean.getTimesheetsFetched() + timesheets.size());
		return new ModelAndView(successView, MAP_KEY, timesheets).addObject(EMP_KEY, employee);
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public TimexJmxBean getTimexJmxBean() {
		return timexJmxBean;
	}

	public void setTimexJmxBean(TimexJmxBean timexJmxBean) {
		this.timexJmxBean = timexJmxBean;
	}

}
