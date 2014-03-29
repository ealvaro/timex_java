package com.timexautoweb.controllers;

import org.springframework.validation.Errors;

import com.timexautoweb.domain.Timesheet;

/**
 * Validator for EnterHoursController
 * 
 * @author Anil Hemrajani
 * @see com.visualpatterns.timex.controller.EnterHoursController
 */
public class EnterHoursValidator implements org.springframework.validation.Validator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.equals(Timesheet.class);
	}

	/**
	 * Validates the Timesheet command object. Ensures that the departmentCode
	 * is specified.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object command, Errors errors) {
		Timesheet timesheet = (Timesheet) command;
		if (timesheet == null)
			return;

		if (timesheet.getDepartment() == null && timesheet.getDepartment_id() == 0)
			errors.reject("error.enterhours.missingdepartment");
	}

}
