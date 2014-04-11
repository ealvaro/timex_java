package com.timexautoweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.domain.TimesheetList;

/**
 * @author Alvaro E. Escobar
 */
public class AccountingController extends SimpleFormController {

	private TimesheetHome timesheetManager;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) {
		List<Timesheet> timesheets = timesheetManager.getTimesheets(Timesheet.APPROVED);
		return new TimesheetList(timesheets);
	}

	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		TimesheetList appTimesheets = (TimesheetList) command;
		List<Timesheet> timesheets = appTimesheets.getTimesheets();
		for (Timesheet t : timesheets) {
			timesheetManager.persist(t);
		}
		return new ModelAndView(getSuccessView());
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}
}
