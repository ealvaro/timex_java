package com.visualpatterns.timex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.visualpatterns.timex.model.TimesheetManager;

/**
 * @author Alvaro E. Escobar
 */
public class PrintTimesheetController extends SimpleFormController {

	public static final String TID = "tid";
	private TimesheetManager timesheetManager;

	/**
	 * Returns instance of object in database matching the value of the "tid"
	 * parameter.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(
	 *      javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		return timesheetManager.getTimesheet(Integer.parseInt(request.getParameter(TID)), false);
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

}
