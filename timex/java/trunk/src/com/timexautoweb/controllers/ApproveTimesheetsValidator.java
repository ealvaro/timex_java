package com.timexautoweb.controllers;

import java.util.List;

import org.springframework.validation.Errors;

import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetList;

/**
 * Validator for ApproveTimesheetsController
 * 
 * @author Anil Hemrajani
 * @see com.visualpatterns.timex.controller.EnterHoursController
 */
public class ApproveTimesheetsValidator implements org.springframework.validation.Validator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.equals(TimesheetList.class);
	}

	/**
	 * Validates the TimesheetList command object. Ensures that the status code
	 * is set for all timesheets.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object command, Errors errors) {
		TimesheetList appTimesheets = (TimesheetList) command;
		List<Timesheet> timesheets = appTimesheets.getTimesheets();
		if (timesheets == null || timesheets.size() <= 0)
			return;
		for (Timesheet t : timesheets)
			if (t.getStatusCode() != Timesheet.APPROVED && t.getStatusCode() != Timesheet.DISAPPROVED && t.getStatusCode() != Timesheet.SUBMITTED)
				errors.reject("error.approvetimesheets.invalidapprove");
	}

}
