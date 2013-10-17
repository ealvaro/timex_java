package com.visualpatterns.timex.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetList;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.TimexJmxBean;

/**
 * @author Alvaro E. Escobar
 */
public class AccountingController extends SimpleFormController {

	private TimesheetManager timesheetManager;
	private TimexJmxBean timexJmxBean;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(
	 *      javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) {
		List<Timesheet> timesheets = timesheetManager.getTimesheets(Timesheet.APPROVED.charAt(0));
		timexJmxBean.setTimesheetsFetched(timexJmxBean.getTimesheetsFetched() + timesheets.size());
		return new TimesheetList(timesheets);
	}

	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) {
		TimesheetList appTimesheets = (TimesheetList) command;
		List<Timesheet> timesheets = appTimesheets.getTimesheets();
		for (Timesheet t : timesheets) {
			timesheetManager.saveTimesheet(t);
		}
		return new ModelAndView(getSuccessView());
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public TimexJmxBean getTimexJmxBean() {
		return timexJmxBean;
	}

	public void setTimexJmxBean(TimexJmxBean timexJmxBean) {
		this.timexJmxBean = timexJmxBean;
	}


}
