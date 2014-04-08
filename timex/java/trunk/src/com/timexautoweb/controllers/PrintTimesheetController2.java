package com.timexautoweb.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class PrintTimesheetController2 implements Controller {
	public static final String TID = "tid";

	private TimesheetHome timesheetManager;

	private ApplicationSecurityManager applicationSecurityManager;

	public static final String MAP_KEY = "command";

	private String successView;

	/**
	 * Returns a list of Timesheet database objects in ModelAndView.
	 * 
	 * @see com.visualpatterns.timex.model.Timesheet
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tid = request.getParameter(TID);
		Timesheet timesheet = null;
		if (tid == null) {
			Employee emp = (Employee) applicationSecurityManager.getEmployee(request);
			timesheet = timesheetManager.findLastOne(emp.getId());
		} else {
			timesheet = timesheetManager.findById(Integer.parseInt(tid));
		}
		return new ModelAndView(getSuccessView(), MAP_KEY, timesheet).addObject("employee", (Employee) applicationSecurityManager.getEmployee(request));
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
