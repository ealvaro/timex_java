package com.visualpatterns.timex.controller;

import java.util.List;

import org.springframework.validation.Errors;

import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetList;

/**
 * Validator for ApproveTimesheetsController
 * 
 * @author Anil Hemrajani
 * @see com.visualpatterns.timex.controller.EnterHoursController
 */
public class ApproveTimesheetsValidator implements
		org.springframework.validation.Validator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.equals(TimesheetList.class);
	}

	/**
	 * Validates the Timesheet command object. Ensures that the departmentCode
	 * is specified.
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
			if (t.getStatusCode() == null
					|| t.getStatusCode().trim().length() < 1)
				errors.reject("error.approvetimesheets.invalidapprove");
	}

}
