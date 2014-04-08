package com.timexautoweb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.ApplicationSecurityManager;

/**
 * @author Alvaro E. Escobar
 */
public class PrintTimesheetController extends SimpleFormController {

	public static final String TID = "tid";
	private TimesheetHome timesheetManager;
	private ApplicationSecurityManager applicationSecurityManager;

	/**
	 * Returns instance of object in database matching the value of the "tid"
	 * parameter.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return timesheetManager.findById(Integer.parseInt(request.getParameter(TID)));
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

}
