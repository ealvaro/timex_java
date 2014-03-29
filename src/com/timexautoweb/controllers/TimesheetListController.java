package com.timexautoweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.ApplicationSecurityManager;

/**
 * Controller for the Timesheet List screen.
 * 
 * @author anil
 */
public class TimesheetListController implements Controller {

	public static final String MAP_KEY = "timesheetsJSPVar";
	public static final String EMP_KEY = "employee";
	private ApplicationSecurityManager applicationSecurityManager;

	private TimesheetHome timesheetManager;
	private String successView;

	private static final Logger logger = Logger.getLogger(TimesheetListController.class);

	/**
	 * Returns a list of Timesheets database objects in ModelAndView.
	 * 
	 * @see com.visualpatterns.timex.model.Timesheet
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee myEmp = (Employee) applicationSecurityManager.getEmployee(request);
		List<Timesheet> timesheets = timesheetManager.getTimesheets(myEmp.getId());
		logger.debug("Showing timesheets for employee id = " + 1);
		return new ModelAndView(getSuccessView(), MAP_KEY, timesheets).addObject(EMP_KEY, myEmp);
	}

	public TimesheetHome getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}


}
