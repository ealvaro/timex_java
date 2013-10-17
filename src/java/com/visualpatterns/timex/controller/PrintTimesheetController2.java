package com.visualpatterns.timex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.ApplicationSecurityManager;
import com.visualpatterns.timex.util.TimexJmxBean;

/**
 * Controller for the Timesheet List screen.
 * 
 * @author anil
 */
public class PrintTimesheetController2 implements Controller {
	public static final String TID = "tid";
	
	private TimesheetManager timesheetManager;

	private ApplicationSecurityManager applicationSecurityManager;

	public static final String MAP_KEY = "command";

	private String successView;

	private TimexJmxBean timexJmxBean;

	/**
	 * Returns a list of Timesheet database objects in ModelAndView.
	 * 
	 * @see com.visualpatterns.timex.model.Timesheet
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tid = request.getParameter(TID);
		Timesheet timesheet = timesheetManager.getTimesheet(Integer.parseInt(tid), false);
		timexJmxBean.setTimesheetsFetched(timexJmxBean.getTimesheetsFetched() + 1);
		return new ModelAndView(getSuccessView(), MAP_KEY, timesheet);
	}

	public TimesheetManager getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public String getSuccessView() {
		return successView;
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
